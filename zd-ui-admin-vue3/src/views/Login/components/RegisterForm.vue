<template>
  <Form
      v-show="getShow"
      :rules="rules"
      :schema="schema"
      class="dark:(border-1 border-[var(--el-border-color)] border-solid)"
      hide-required-asterisk
      label-position="top"
      size="large"
      @register="register"
  >
    <template #title>
      <LoginFormTitle style="width: 100%"/>
    </template>

    <template #code="form">
      <el-input
          v-model="form['code']"
          :placeholder="t('login.codePlaceholder')"
          :prefix-icon="iconCircleCheck"
      >
        <!-- <el-button class="w-[100%]"> -->
        <template #append>
                  <span
                      v-if="mobileCodeTimer <= 0"
                      class="getMobileCode"
                      style="cursor: pointer"
                      @click="getSmsCode"
                  >
                    {{ t('login.getSmsCode') }}
                  </span>
          <span v-if="mobileCodeTimer > 0" class="getMobileCode" style="cursor: pointer">
                    {{ mobileCodeTimer }}秒后可重新获取
                  </span>
        </template>
      </el-input>
    </template>

    <template #register>
      <div class="w-[100%]">
        <XButton
            :loading="loading"
            :title="t('login.register')"
            class="w-[100%]"
            type="primary"
            @click="loginRegister()"
        />
      </div>
      <div class="mt-15px w-[100%]">
        <XButton :title="t('login.hasUser')" class="w-[100%]" @click="handleBackLogin()"/>
      </div>
    </template>
  </Form>
</template>
<script lang="ts" setup>
import type {FormRules} from 'element-plus'

import {useForm} from '@/hooks/web/useForm'
import {useValidator} from '@/hooks/web/useValidator'
import LoginFormTitle from './LoginFormTitle.vue'
import {LoginStateEnum, useLoginState} from './useLogin'
import {FormSchema} from '@/types/form'
import * as LoginApi from "@/api/login";
import * as authUtil from '@/utils/auth'

const {currentRoute,push, replace} = useRouter()

defineOptions({name: 'RegisterForm'})

const {t} = useI18n()
const {required} = useValidator()
const {register, elFormRef, methods} = useForm()
const {handleBackLogin, getLoginState} = useLoginState()
const getShow = computed(() => unref(getLoginState) === LoginStateEnum.REGISTER)

const schema = reactive<FormSchema[]>([
  {
    field: 'title',
    colProps: {
      span: 24
    }
  },
  {
    field: 'nickname',
    label: t('login.username'),
    value: '',
    component: 'Input',
    colProps: {
      span: 24
    },
    componentProps: {
      placeholder: t('login.usernamePlaceholder')
    }
  },
  {
    field: 'mobile',
    label: t('login.mobileNumber'),
    value: '',
    component: 'Input',
    colProps: {
      span: 24
    },
    componentProps: {
      placeholder: t('login.mobileNumberPlaceholder')
    }
  },
  {
    field: 'code',
    label: t('login.code'),
    colProps: {
      span: 24
    }
  },
  {
    field: 'password',
    label: t('login.password'),
    value: '',
    component: 'InputPassword',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      strength: true,
      placeholder: t('login.passwordPlaceholder')
    }
  },
  {
    field: 'check_password',
    label: t('login.checkPassword'),
    value: '',
    component: 'InputPassword',
    colProps: {
      span: 24
    },
    componentProps: {
      style: {
        width: '100%'
      },
      strength: true,
      placeholder: t('login.passwordPlaceholder')
    }
  },
  {
    field: 'register',
    colProps: {
      span: 24
    }
  }
])
const message = useMessage()
const validatePass = async (rule: any, value: any, callback: any) => {
  const formData = await methods.getFormData()
  if (value === '') {
    callback(new Error('请输入密码'))
  } else {
    if (formData.check_password !== '') {
      if (!elFormRef.value) return
      elFormRef.value.validateField('check_password', () => null)
    }
    callback()
  }
}
const validatePass2 = async (rule: any, value: any, callback: any) => {
  const formData = await methods.getFormData()
  console.log(formData)
  if (value === '') {
    callback(new Error('请再输入一次密码'))
  } else if (value !== formData.password) {
    callback(new Error("两次输入密码不一致！"))
  } else {
    callback()
  }
}

const rules = reactive<FormRules<typeof ruleForm>>({
  username: [required()],
  password: [{validator: validatePass, trigger: 'blur'}],
  check_password: [{validator: validatePass2, trigger: 'blur'}],
  code: [required()]
})

const loading = ref(false)
const mobileCodeTimer = ref(0)
const redirect = ref<string>('')
const getSmsCode = async () => {
  await getTenantId()
  smsVO.smsCode.mobile = loginData.loginForm.mobileNumber
  await LoginApi.sendSmsCode(smsVO.smsCode).then(async () => {
    message.success(t('login.SmsSendMsg'))
    // 设置倒计时
    mobileCodeTimer.value = 60
    let msgTimer = setInterval(() => {
      mobileCodeTimer.value = mobileCodeTimer.value - 1
      if (mobileCodeTimer.value <= 0) {
        clearInterval(msgTimer)
      }
    }, 1000)
  })
}

const loginRegister = async () => {
  const formRef = unref(elFormRef)
  authUtil.setTenantId(1)//固定使用系统租户，登录后再处理
  formRef?.validate(async (valid) => {
    if (valid) {
      try {
        loading.value = true
        const data = await methods.getFormData()
        const res = await LoginApi.register(data)
        if (!res) {
          return
        }
        await ElMessageBox.confirm('请继续完善资料并进行企业账户认证，以获取碳普惠注登系统平台权限。', '注册成功', {
          confirmButtonText: '继续开户',
          cancelButtonText: '下次再说',
          type: 'warning'
        })
        authUtil.setToken(res)
        await replace({name:"TenantAuth",query:{"tenantId":"1"}})
      } finally {
        loading.value = false
      }
    }
  })
}
</script>
