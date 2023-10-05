

<template>
  <el-card shadow="always">
    <el-page-header @back="back" class="ml10">
      <template #content>
        <div class="flex items-center">
          <el-avatar
              :size="32"
              class="mr-3"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
          <span class="text-large font-600 mr-3"> 企业账户开通 </span>
          <el-tag>碳普惠登记平台</el-tag>
        </div>
      </template>
    </el-page-header> </el-card>

  <el-scrollbar style="width: 100%;">
    <div class="common-layout ml5 mr5 mt5 mb34">

      <el-container>

        <el-header>

          <el-steps :active="active" process-status="process" finish-status="success" align-center
                    style="margin-top: 20px">
            <el-step title="填写开户信息" :icon="Edit"/>
            <el-step title="提交证明材料" :icon="Upload"/>
            <el-step title="查看审核结果" :icon="Search"/>
          </el-steps>
        </el-header>
          <div v-show="active==0" class="mt5 mr30 ml30">
            <input-form :value="formData"  @submit="submit"  @prev="back"></input-form>
          </div>
          <div v-show="active==1" class="mt5 mr30 ml30">
            <upload-form :value="formData" @change="handleChange"  @submit="submit" @prev="prev"></upload-form>
          </div>
          <div v-show="active==2" class="mt5 mr30 ml30">
            <checkout-form :value="formData" @submit="submit" @prev="prev"></checkout-form>
          </div>

      </el-container>
    </div>

  </el-scrollbar>
</template>

<script setup lang="tsx">
defineOptions({name: 'TenantAuth'})
import {useUserStore} from "@/store/modules/user";
import {ref} from 'vue'
import {Edit, Search, Upload} from '@element-plus/icons-vue'
import InputForm from "@/views/system/tenant/components/InputForm.vue";
import UploadForm from "@/views/system/tenant/components/UploadForm.vue";
import CheckoutForm from "@/views/system/tenant/components/CheckoutForm.vue";
import {CommonStatusEnum} from "@/utils/constants";
import {useTagsViewStore} from "@/store/modules/tagsView";
import {ElMessageBox} from "element-plus";

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
  contact_name: 'test',
  contact_mobile: 17698473827,
  contact_id_card: undefined,
  contact_authorize_file: undefined,
  contact_id_card_file_front: undefined,
  contact_id_card_file_back: undefined,
  status: CommonStatusEnum.ENABLE
})
const {currentRoute, push} = useRouter()
const active = ref(1)


const { t } = useI18n()

const userStore = useUserStore()

const tagsViewStore = useTagsViewStore()



const handleChange=(e)=>{
  formData.value[e.label]=e.value
  console.log(e)
  console.log(formData)
  console.log("result")
}

const submit=(e)=>{
  //TODO:WJJ 待补充持久化逻辑
  formData.value=e.data
  next()
}
const next = () => {
  if (active.value++ >= 2) active.value = 2
}
const prev = () => {
  console.log('prev')
  if (active.value-- ==0) active.value = 0
}
const back = () => {
  loginOut()
}

const loginOut = () => {
  ElMessageBox.confirm(t('common.loginOutMessage'), t('common.reminder'), {
    confirmButtonText: t('common.ok'),
    cancelButtonText: t('common.cancel'),
    type: 'warning'
  })
      .then(async () => {
        await userStore.loginOut()
        tagsViewStore.delAllViews()
        replace('/login?redirect=/index')
      })
      .catch(() => {})
}
</script>

<style lang="less" >
 .el-header{
   min-width: 1333px;
 }
 .el-main{
   min-width: 1170px;
 }

 .account-auth__form-toolbar {
   position: fixed;
   left: 0;
   right: 0;
   bottom: 0;
   z-index: 0;
   display: flex;
   flex-direction: row;
   justify-content: center;
   align-items: center;
   min-height: 32px;
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
