<template>

  <el-main >
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
            <el-col :span="8"
            >
              <el-form-item label="营业执照" prop="bln_file">
                <one-image-upload :img="formData.bln_file" name="bln_file" @change="handleChange"></one-image-upload>
              </el-form-item>
            </el-col>
            <el-col :span="8"
            >
              <el-form-item label="法人身份证正面" prop="bln_file">
                <one-image-upload :img="formData.corporate_id_card_file_front" name="corporate_id_card_file_front"
                                  @change="handleChange"></one-image-upload>
              </el-form-item>
            </el-col>
            <el-col :span="8"
            >
              <el-form-item label="法人身份证反面" prop="bln_file">
                <one-image-upload :img="formData.corporate_id_card_file_back" name="corporate_id_card_file_back"
                                  @change="handleChange"></one-image-upload>
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </el-form>
  </el-main>
  <el-footer>
    <div class="account-auth__form-toolbar text-center mt-10px">
      <el-button style="margin-top: 12px" @click="next">Next step</el-button>
    </div>
  </el-footer>
</template>
<script setup lang="tsx">
import * as PostApi from "@/api/system/post";

defineOptions({name: 'UploadForm'})
import {ref} from 'vue'
import {reactive} from 'vue'
import {CommonStatusEnum} from "@/utils/constants";
import OneImageUpload from "@/views/system/tenant/components/OneImageUpload.vue";
import InputForm from "@/views/system/tenant/components/InputForm.vue";
import CheckoutForm from "@/views/system/tenant/components/CheckoutForm.vue";

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


const emit = defineEmits(['change','submit'])

const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formData = ref({
  id: undefined,
  name: undefined,
  three_in_one: true,
  uscc: undefined,
  bln: undefined,
  bln_file: undefined,
  unit_code: undefined,
  unit_code_file: undefined,
  tax_id: undefined,
  tax_id_file: undefined,
  industry: "",
  industry_text: undefined,
  registration_date: undefined,
  registered_capital: undefined,
  registered_province: undefined,
  registered_city: undefined,
  registered_address: undefined,
  corporate: undefined,
  corporate_id_card: undefined,
  corporate_id_card_file_front: undefined,
  corporate_id_card_file_back: undefined,
  enterprise_nature: undefined,
  contact_name: undefined,
  contact_mobile: undefined,
  contact_id_card: undefined,
  contact_authorize_file: undefined,
  contact_id_card_file_front: undefined,
  contact_id_card_file_back: undefined,
  status: CommonStatusEnum.ENABLE
})
const formRules = reactive({
  name: [{required: true, message: '租户名不能为空', trigger: 'blur'}],
})

const formRef = ref() // 表单 Ref
const handleChange = (e) => {
  formData.value[e.name] = e.url;

  emit('change', {label: e.name, value: e.url})
  console.log(e)
  console.log(1)
}

const next = () => {
  if (unref(formRef)?.validate()) {
    console.log(formData)
    emit('submit', {data:formData})
  }
}
</script>
<style lang="less" scoped>

.account-auth__form-toolbar {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
  padding: 24px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  min-height: 32px;
  //background-color: white;
}

:deep(.is-required--item) {
  position: relative;

  &::before {
    margin-right: 4px;
    color: var(--el-color-danger);
    content: '*';
  }

}
.el-collapse-item-header {
  &__title {
    .content {
      font-size: large;
    }

    .info {
      color: #b4b2b2;
      margin-left: 5px;
    }

    .el-link {
      display: inline;
      line-height: initial;
    }

    &::after {
      position: absolute;
      top: 10px;
      left: -10px;
      width: 4px;
      height: 50%;
      background: var(--el-color-primary);
      content: '';
    }
  }
}

.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
  padding: 0;
}
</style>
