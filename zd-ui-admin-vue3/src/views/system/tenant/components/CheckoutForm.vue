

<template>
  <el-main>

  </el-main>
  <el-footer>
    <el-card class="account-auth__form-toolbar text-center mt-10px" shadow="always">
      <el-button style="margin-top: 12px" @click="prev">返回</el-button>
      <el-button style="margin-top: 12px" type="primary"  @click="next">重新认证</el-button>
      <el-button style="margin-top: 12px" type="primary" @click="next">登录系统</el-button>
    </el-card>
  </el-footer>
</template>
<script setup lang="tsx">
import * as PostApi from "@/api/system/post";

defineOptions({name: 'CheckoutForm'})
import {ref} from 'vue'
import {reactive} from 'vue'
import {DICT_TYPE, getIntDictOptions, getStrDictOptions} from "@/utils/dict";
import {CommonStatusEnum} from "@/utils/constants";
import {getAreaTree} from '@/api/system/area'

const emit = defineEmits(['change', 'submit','prev'])

const {currentRoute, push} = useRouter()

const active = ref(0)
const activeNames = ref(['1', '2'])

const areaTreeOptions = ref([])

// 表格的数据
const list = ref([])

/**
 * 获得数据列表
 */
const getList = async () => {
  areaTreeOptions.value = await getAreaTree()
}

const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formData = ref({
  id: undefined,
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
  registeredAddress: undefined,
  corporate: undefined,
  corporateIdCard: undefined,
  corporateIdCardFileFront: undefined,
  corporateIdCardFileBack: undefined,
  enterpriseNature: undefined,
  contactName: undefined,
  contactMobile: undefined,
  contactIdCard: undefined,
  contactAuthorizeFile: undefined,
  contactIdCardFileFront: undefined,
  contactIdCardFileBack: undefined,
  status: CommonStatusEnum.ENABLE
})
const formRules = reactive({
  name: [{required: true, message: '租户名不能为空', trigger: 'blur'}],
  packageId: [{required: true, message: '租户套餐不能为空', trigger: 'blur'}],
  contactName: [{required: true, message: '联系人不能为空', trigger: 'blur'}],
  status: [{required: true, message: '租户状态不能为空', trigger: 'blur'}],
  accountCount: [{required: true, message: '账号额度不能为空', trigger: 'blur'}],
  expireTime: [{required: true, message: '过期时间不能为空', trigger: 'blur'}],
  domain: [{required: true, message: '绑定域名不能为空', trigger: 'blur'}],
  username: [{required: true, message: '用户名称不能为空', trigger: 'blur'}],
  password: [{required: true, message: '用户密码不能为空', trigger: 'blur'}]
})

const formRef = ref() // 表单 Ref
const next = () => {
  emit('submit', {data:formData})
}
const prev = async () => {
  emit('prev',{})
}

/** 初始化 **/
onMounted(() => {
  getList()

})
</script>
<style lang="less" scoped>

</style>
