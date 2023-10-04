<template>
  <el-upload
      ref="uploadRef"
      class="upload-demo"
      :class="{ haveImg:fileLoading|| !!file.url }"
      list-type="picture-card"
      drag
      :headers="uploadHeaders"
      :limit="1"
      :before-upload="handleBeforeUpload"
      :on-preview="handlePictureCardPreview"
      :on-remove="()=>{handleRemove('bln_file')}"
      :on-error="submitFormError"
      :on-progress="handleProgress"
      :on-exceed="handleExceed"
      :on-success="(a,b)=>{submitFormSuccess(a,b,'bln_file')}"
      accept=".jpg, .png, .gif"
      :action="url"
  >
    <el-icon class="el-icon--upload">
      <upload-filled/>
    </el-icon>
    <div class="el-upload__text">
      <em>点击上传</em> / 拖拽到此区域
    </div>

  </el-upload>
  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
</template>
<script setup lang="tsx">
import * as PostApi from "@/api/system/post";

defineOptions({name: 'OneImageUpload'})
import {ref} from 'vue'
import {UploadFilled} from '@element-plus/icons-vue'
import {getAccessToken, getTenantId} from '@/utils/auth'
import {UploadProps} from "element-plus";

const url = import.meta.env.VITE_UPLOAD_URL
const uploadHeaders = ref() // 上传 Header 头
const fileList = ref([]) // 文件列表
const data = ref({path: ''})
const uploadRef = ref()
const {t} = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const fileLoading=ref(false)

const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const file = ref({url:undefined})
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}
const handleRemove = (name) => {
  console.log(name)
  file.value.url = undefined;
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


/** 文件上传成功处理 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitFormSuccess = (response,
                           uploadFile, field) => {
  // 清理
  formLoading.value = false
  fileLoading.value = false
  // unref(uploadRef)?.clearFiles()
  // 提示成功，并刷新
  message.success(t('common.createSuccess'))
  console.log(response)
  console.log(uploadFile)
  console.log(field)
  file.value.url = response.data
  emit('success',file)
}

/** 上传错误提示 */
const submitFormError = (): void => {
  message.error('上传失败，请您重新上传！')
  formLoading.value = false
  fileLoading.value = false
  file.value.url=undefined
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
}
</script>
<style lang="less" scoped>

:deep .el-upload--picture-card {
  --el-upload-picture-card-size: 230px;
  border: none;
  width: 340px;
  min-width: 148px;
}
:deep .el-upload-dragger{
  height: 230px;
}

.haveImg {
  :deep .el-upload--picture-card {
    display: none;
  }
}
:deep .el-upload-list__item-actions{
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