<template>

  <el-main>
    <el-form
        ref="formRef"
        v-loading="formLoading"
        :model="formData"
        :rules="formRules"
        label-position="top"
        @submit.native.prevent
    >
      <el-collapse v-model="activeNames">
        <el-collapse-item title="Consistency" name="1" @click.stop>
          <template #title>
            <div :class="[`el-collapse-item-header__title`, 'relative font-18px font-bold ml-10px']">
              <div class="flex items-center">
                <div class="content">证明材料</div>
              </div>
            </div>
          </template>
          <el-row>
            <el-col :span="8">
              <el-form-item label="营业执照" prop="blnFile">
                <one-image-upload v-model="formData.blnFile"></one-image-upload>
              </el-form-item>
            </el-col>

            <el-col :span="8" v-if="!formData.threeInOne">
              <el-form-item label="组织机构代码证" prop="unitCodeFile">
                <one-image-upload v-model="formData.unitCodeFile"></one-image-upload>
              </el-form-item>
            </el-col>
            <el-col :span="8" v-if="!formData.threeInOne">
              <el-form-item label="税务登记证" prop="taxIdFile">
                <one-image-upload v-model="formData.taxIdFile"></one-image-upload>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="法人身份证正面" prop="corporateIdCardFileFront">
                <one-image-upload remark="身份证正面"
                                  v-model="formData.corporateIdCardFileFront"></one-image-upload>

              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="法人身份证反面" prop="corporateIdCardFileBack">
                <one-image-upload remark="身份证反面"
                                  v-model="formData.corporateIdCardFileBack"></one-image-upload>

              </el-form-item>
            </el-col>
          </el-row>

          <el-row v-if="formData.contactIdCard!==formData.corporateIdCard">
            <el-col :span="8">
              <el-form-item label="账户代表授权委托书" prop="contactAuthorizeFile">
                <one-image-upload v-model="formData.contactAuthorizeFile"></one-image-upload>
                <template #label>
                  账户代表授权委托书（
                  <el-link type="primary" @click="handleDownloadTemp">点击下载模板</el-link>
                  ）
                </template>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="账户代表身份证正面" prop="contactIdCardFileFront">
                <one-image-upload remark="身份证正面"
                                  v-model="formData.contactIdCardFileFront"></one-image-upload>

              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="账户代表身份证反面" prop="contactIdCardFileBack">
                <one-image-upload remark="身份证反面" v-model="formData.contactIdCardFileBack"></one-image-upload>

              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </el-form>
  </el-main>
  <el-footer>
    <el-card class="account-auth__form-toolbar text-center mt-10px" shadow="always">
      <el-button style="margin-top: 12px" @click="prev">上一步</el-button>
      <el-button style="margin-top: 12px" type="primary" @click="next">提交认证</el-button>
    </el-card>
  </el-footer>
</template>
<script setup lang="tsx">
import * as PostApi from "@/api/system/post";

defineOptions({name: 'UploadForm'})
import {ref} from 'vue'
import {reactive} from 'vue'
import OneImageUpload from "@/views/system/tenant/components/OneImageUpload.vue";
import {propTypes} from "@/utils/propTypes";
import {CommonStatusEnum} from "@/utils/constants";

const props = defineProps({
  value: propTypes.object.def({})
})
const emit = defineEmits(['change', 'submit', 'prev'])

const dialogVisible = ref(false)
const disabled = ref(false)
const url = import.meta.env.VITE_UPLOAD_URL
const fileList = ref([]) // 文件列表
const data = ref({path: ''})
const {t} = useI18n() // 国际化
const message = useMessage() // 消息弹窗
const {currentRoute, push} = useRouter()

const active = ref(0)
const activeNames = ref(['1', '2'])


const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formRules = reactive({
  blnFile: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  unitCodeFile: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  taxIdFile: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  corporateIdCardFileFront: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  corporateIdCardFileBack: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  contactAuthorizeFile: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  contactIdCardFileFront: [{required: true, message: '此文件还未上传', trigger: 'blur'}],
  contactIdCardFileBack: [{required: true, message: '此文件还未上传', trigger: 'blur'}]
})
const formData = props.value
const formRef = ref() // 表单 Ref
const handleDownloadTemp = () => {
  message.success("下载模板")
}
const next = async () => {
  const valid = await formRef.value.validate()
  if (!valid) return
  console.log('form', formData)
  emit('submit', {data: formData})
}
const prev = async () => {
  emit('prev', {})
}
</script>
<style lang="less" scoped>

</style>
