<template>
  <el-upload
      v-model:file-list="fileList"
      ref="uploadRef"
      class="upload-demo"
      :class="{ haveImg:fileLoading|| fileList.length>0 }"
      list-type="picture-card"
      drag
      :headers="uploadHeaders"
      :limit="1"
      :before-upload="handleBeforeUpload"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-error="submitFormError"
      :on-progress="handleProgress"
      :on-exceed="handleExceed"
      :on-success="submitFormSuccess"
      :action="url"
  >
    <el-icon class="el-icon--upload">
      <upload-filled/>
    </el-icon>
    <div class="el-upload__text">
      {{ props.remark }}
    </div>
    <div class="el-upload__text">
      <em>点击上传</em> / 拖拽到此区域
    </div>
    <template #tip>
      <div class="el-upload__tip">
        JPG或PNG格式，文件大小不超过10M
      </div>
    </template>
  </el-upload>
  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image"/>
  </el-dialog>
</template>
<script setup lang="tsx">

defineOptions({name: 'OneImageUpload'})
import {ref} from 'vue'
import {UploadFilled} from '@element-plus/icons-vue'
import {getAccessToken, getTenantId} from '@/utils/auth'
import {UploadProps, UploadUserFile} from "element-plus";
import {propTypes} from "@/utils/propTypes";

const props = defineProps({
  remark: propTypes.string.def(''),
  modelValue: propTypes.string.def('')
})

const emit = defineEmits(['update:modelValue'])

const url = import.meta.env.VITE_UPLOAD_URL
const uploadHeaders = ref() // 上传 Header 头
const uploadRef = ref()
const {t} = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const fileLoading = ref(false)

const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用

const fileList = props.modelValue ? ref<UploadUserFile[]>([
  {
    name: props.modelValue,
    url: props.modelValue,
  }
]) : ref<UploadUserFile[]>([])

const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}
const handleRemove = () => {
  emit('update:modelValue', undefined)
  unref(uploadRef)?.clearFiles()
}
const handleProgress = (name) => {
}

const formRef = ref() // 表单 Ref
// 提交请求
uploadHeaders.value = {
  Authorization: 'Bearer ' + getAccessToken(),
  'tenant-id': getTenantId()
}

/** 上传前 */
const handleBeforeUpload = (e) => {
  fileLoading.value = true
  console.log(`------`, e)
  const isJPG = e.type === 'image/jpeg'
  const isPNG = e.type === 'image/png'
  const isLt500k = e.size / 1024 / 1024 <= 1
  if (e) {
    if (isJPG || isPNG) {
    } else {
      message.error('上传图片只能是 JPG或PNG 格式!')
      fileLoading.value = false
    }
  }

  if (!isLt500k) {
    message.error('上传图片大小不能超过 500kb!')
    fileLoading.value = false
  }
  // 返回图是jpg或png格式并且大小不超过2MB
  return (isJPG || isPNG) && isLt500k
}


const submitFormSuccess = (response,
                           uploadFile, field) => {
  // 清理
  formLoading.value = false
  fileLoading.value = false
  // 提示成功，并刷新
  message.success(t('common.createSuccess'))
  emit('update:modelValue', response.data)
}

/** 上传错误提示 */
const submitFormError = (): void => {
  message.error('上传失败，请您重新上传！')
  formLoading.value = false
  fileLoading.value = false
  uploadRef.value?.clearFiles()
}

/** 重置表单 */
const resetForm = () => {
  // 重置上传状态和文件
  formLoading.value = false
  uploadRef.value?.clearFiles()
}

/** 文件数超出提示 */
const handleExceed = (): void => {
  message.error('最多只能上传一个文件！')

  fileLoading.value = false
}
</script>
<style lang="less" scoped>

:deep .el-upload--picture-card {
  --el-upload-picture-card-size: 230px;
  border: none;
  width: 340px;
  min-width: 148px;
}

:deep .el-upload-dragger {
  height: 230px;
}

.haveImg {
  :deep .el-upload--picture-card {
    display: none;
  }
}

:deep .el-upload-list__item-actions {
  --el-upload-list-picture-card-size: 230px;

  .el-upload-list__item {
    width: 340px;
  }
}

:deep .el-upload-list--picture-card {
  --el-upload-list-picture-card-size: 230px;

  .el-upload-list__item {
    width: 340px;
  }
}
</style>
