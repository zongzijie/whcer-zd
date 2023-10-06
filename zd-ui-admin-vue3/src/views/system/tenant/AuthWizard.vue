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
    </el-page-header>
  </el-card>

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
        <div v-if="active==0" class="mt5 mr30 ml30">
          <input-form :value="formData" @submit="submit" @prev="back" @kuaijie="kuaijie"></input-form>
        </div>
        <div v-if="active==1" class="mt5 mr30 ml30">
          <upload-form :value="formData"  @submit="submit" @prev="prev"></upload-form>
        </div>
        <div v-if="active==2" class="mt5 mr30 ml30">
          <checkout-form :value="formData" @submit="submit" @prev="prev"></checkout-form>
        </div>

      </el-container>
    </div>

  </el-scrollbar>
</template>

<script setup lang="tsx">
import {authTenantCreate, authTenantUpdate, getTenant} from "@/api/system/tenant";
import {useUserStore} from "@/store/modules/user";
import {ref} from 'vue'
import {Edit, Search, Upload} from '@element-plus/icons-vue'
import InputForm from "@/views/system/tenant/components/InputForm.vue";
import UploadForm from "@/views/system/tenant/components/UploadForm.vue";
import CheckoutForm from "@/views/system/tenant/components/CheckoutForm.vue";
import {CommonStatusEnum} from "@/utils/constants";
import {useTagsViewStore} from "@/store/modules/tagsView";
import {getUserProfile} from '@/api/system/user/profile'
import {ElInput, ElMessageBox} from "element-plus";
import {getConfigKey} from "@/api/infra/config";

defineOptions({name: 'TenantAuth'})

const formData = ref({
  id: undefined,
  username: undefined,
  password: undefined,
  packageId: undefined,
  expireTime: undefined,
  accountCount: undefined,
  name: undefined,
  threeInOne: true,
  uscc: undefined,
  bln: undefined,
  blnFile: undefined,
  unitCode: undefined,
  unitCodeFile: undefined,
  taxId: undefined,
  taxIdFile: undefined,
  industry: "",
  industryText: undefined,
  registrationDate: undefined,
  registeredCapital: undefined,
  registeredProvince: undefined,
  registeredCity: undefined,
  registeredCounty: undefined,
  registeredAddress: undefined,
  corporate: undefined,
  corporateIdCard: undefined,
  corporateIdCardFileFront: undefined,
  corporateIdCardFileBack: undefined,
  enterpriseNature: undefined,
  contactName: 'test',
  contactMobile: 17698473827,
  contactIdCard: undefined,
  contactAuthorizeFile: undefined,
  contactIdCardFileFront: undefined,
  contactIdCardFileBack: undefined,
  status: CommonStatusEnum.ENABLE
})
const {currentRoute, replace, push} = useRouter()
const active = ref(0)


const {t} = useI18n()

const userStore = useUserStore()
const message = useMessage()
const tagsViewStore = useTagsViewStore()

const submit = async (e) => {
  console.log(e.data.id)
  Object.assign(formData.value,e.data)
  if(e.data.id){
    const result=await update(e)
    if (!result){
      message.alertError("保存失败，请联系管理员。")
      return
    }
  }else{
    e.data.id = await create(e)
    const path = currentRoute.value.path
    let query = currentRoute.value.query
    query.tenantId = e.data.id
    await replace({path, query})
  }
  next()
}


const create = async (e) => {
  formData.value = e.data
  formData.value.username = e.data.contactMobile
  formData.value.password = await getConfigKey("sys.user.init-password")
  formData.value.accountCount = 9999
  formData.value.expireTime = new Date("2123-01-01").getTime()
  formData.value.packageId = 111
  return await authTenantCreate(formData.value)
}

const update = async (e) => {
  return await authTenantUpdate(e.data)
}

const next = () => {
  if (active.value++ >= 2) active.value = 2
}
const prev = () => {
  console.log('prev')
  if (active.value-- == 0) active.value = 0
}
const back = () => {
  loginOut()
}

const getUserInfo = async () => {
  const {nickname, mobile} = await getUserProfile()
  formData.value.contactName = nickname
  formData.value.contactMobile = mobile
}

const getTenantInfo = async (tenantId) => {
  const data=await getTenant(tenantId)
  Object.assign(formData.value,data)
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
        await replace('/login?redirect=/index')
      })
      .catch(() => {
      })
}

const kuaijie = async () => {
  //拉取湖北注登的数据
  const name = ref<string>('')
  const pass = ref<string>('')
  await ElMessageBox({
    title: '湖北注登系统帐号验证',
    // Should pass a function if VNode contains dynamic props
    message: () =>
        h('p', null, [
          h('span', null, '帐号：'),
          h(ElInput, {
            modelValue: name.value,
            'onUpdate:modelValue': (val: boolean | string | number) => {
              name.value = val
            },
          }),
          h('p', null, ''),
          h('span', null, '密码'),
          h(ElInput, {
            modelValue: pass.value,
            type: "password",
            'onUpdate:modelValue': (val: boolean | string | number) => {
              pass.value = val
            },
          }),
        ]),
    showCancelButton: true,
    confirmButtonText: '提交',
    cancelButtonText: '取消',
    beforeClose: (action, instance, done) => {
      if (action === 'confirm') {
        if (name.value == "" || pass.value == "") {
          message.alertError("请填写帐号密码后提交")
          return
        }
        instance.confirmButtonLoading = true
        instance.confirmButtonText = '验证中...'
        setTimeout(() => {
          done()
          setTimeout(() => {
            instance.confirmButtonLoading = false
          }, 300)
        }, 3000)
      } else {
        done()
      }
    },
  })
}

onMounted(async () => {
  const {query: {tenantId}} = currentRoute.value
  console.log(tenantId)
  if (tenantId) {
    await getTenantInfo(tenantId)
  } else {
    //企业首次填写时才提示，否则需要自己主动点击快捷注册。
    await getUserInfo()
    await ElMessageBox.confirm(t('已开通湖北注册登记系统账户的企业可快捷注册'), t('注册提示'), {
      confirmButtonText: t('已开通湖北注登系统账户'),
      cancelButtonText: t('未开通湖北注登系统账户'),
      type: 'warning'
    })
    await kuaijie()
  }

})
</script>

<style lang="less">
.el-header {
  min-width: 1333px;
}

.el-main {
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
