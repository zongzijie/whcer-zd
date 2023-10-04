

<template>
  <el-main>

  </el-main>
  <el-footer>
    <div class="account-auth__form-toolbar text-center mt-10px">
      <el-button style="margin-top: 12px" @click="next">Next step</el-button>
    </div>
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

const emit = defineEmits(['change', 'submit'])

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
const kuaijie = () => {
  console.log(11)
}
const handleChange = () => {
  console.log(11)
}

const back = () => {
  push({path: "/"})
}

/** 初始化 **/
onMounted(() => {
  getList()

})
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
