

<template>
  <el-main>
    <el-form
        ref="formRef"
        v-loading="formLoading"
        :model="formData"
        :rules="formRules"
        label-width="130px"
        @submit.native.prevent
    >
      <el-collapse v-model="activeNames">
        <el-collapse-item title="Consistency" name="1" @click.stop="handleChange">
          <template #title>
            <div :class="[`el-collapse-item-header__title`, 'relative font-18px font-bold ml-10px']">
              <div class="flex items-center">
                <div class="content">企业信息</div>
                <div class="info">
                  （已开通湖北注册登记系统账户的企业可点击此处进行
                  <el-link @click.stop="kuaijie" type="primary">快捷注册</el-link>
                  ）
                </div>
              </div>
            </div>
          </template>
          <el-row>
            <el-col :span="12"
            >
              <el-form-item label="企业名称" prop="name">
                <el-input v-model="formData.name" placeholder="请输入企业名称"/>
              </el-form-item>
            </el-col>
            <el-col :span="12"
            >
              <el-form-item label="三证合一" prop="three_in_one">
                <el-switch v-model="formData.three_in_one"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row v-if="formData.three_in_one">
            <el-col :span="12">
              <el-form-item label="统一社会信用代码" prop="uscc">
                <el-input v-model="formData.uscc" placeholder="请填写统一社会信用代码"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="所属行业" prop="industry">
                <el-select v-model="formData.industry" placeholder="请选择所属行业">
                  <el-option
                      v-for="dict in getStrDictOptions(DICT_TYPE.SYSTEM_TENANT_INDUSTRY)"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <template v-else>
            <el-row>
              <el-col :span="12">
                <el-form-item label="组织机构代码" prop="unit_code">
                  <el-input v-model="formData.unit_code" placeholder="请输入组织机构代码"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="营业执照注册号" prop="bln">
                  <el-input v-model="formData.bln" placeholder="请输入营业执照注册号"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="税务登记证号" prop="tax_id">
                  <el-input v-model="formData.tax_id" placeholder="请输入税务登记证号"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="所属行业" prop="industry">
                  <el-select v-model="formData.industry" placeholder="请选择所属行业">
                    <el-option
                        v-for="dict in getStrDictOptions(DICT_TYPE.SYSTEM_TENANT_INDUSTRY)"
                        :key="dict.value"
                        :label="dict.label"
                        :value="dict.value"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </template>

          <el-row>
            <el-col :span="12">
              <el-form-item label="注册日期" prop="registration_date">
                <el-date-picker
                    v-model="formData.registration_date"
                    type="date"
                    placeholder="请选择日期"
                    style="width: 100%"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="注册资本" prop="registered_capital">
                <el-input type="number" v-model="formData.registered_capital" placeholder="请填写注册资本">
                  <template #append>万元</template>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="法人信息" prop="corporate">
                <el-input v-model="formData.corporate" placeholder="请输入法人信息"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="法人身份证号" prop="corporate_id_card">
                <el-input v-model="formData.corporate_id_card" placeholder="请输入法人身份证号"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="企业性质" prop="enterprise_nature">
                <el-radio-group v-model="formData.enterprise_nature" class="ml-4">
                  <el-radio v-for="dict in getStrDictOptions(DICT_TYPE.SYSTEM_TENANT_ENTERPRISE_NATURE)"
                            :key="dict.value" :label="dict.value" size="large">{{ dict.label }}
                  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="注册地址" prop="registered_address">
                <el-input
                    v-model="formData.registered_address"
                    placeholder="请填写最新的详细的办公地址，可与证照地址不一致"
                    class="input-with-select"
                >
                  <template #prepend>
                    <el-cascader
                        v-model="formData.registered_address"
                        placeholder="请选择"
                        :options="areaTreeOptions"
                        :props="{ value: 'id',label: 'name'}"
                        filterable
                    />
                  </template>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
        <el-collapse-item title="Feedback" name="2">
          <template #title>
            <div :class="[`el-collapse-item-header__title`, 'relative font-18px font-bold ml-10px']">
              <div class="flex items-center">
                <div class="content">账户代表信息</div>
                <div class="info">
                  （账户代表作为注登系统的管理员角色，拥有系统最高权限）
                </div>
              </div>
            </div>
          </template>
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名" prop="contact_name">
                <el-input v-model="formData.contact_name" disabled placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="手机号" prop="contact_mobile">
                <el-input v-model="formData.contact_mobile" disabled placeholder=""/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="身份证号码" prop="corporate">
                <el-input v-model="formData.corporate" placeholder="请填写身份证号码"/>
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

defineOptions({name: 'InputForm'})
import {ref} from 'vue'
import {reactive} from 'vue'
import {DICT_TYPE, getIntDictOptions, getStrDictOptions} from "@/utils/dict";
import {CommonStatusEnum} from "@/utils/constants";
import {getAreaTree} from '@/api/system/area'

const {currentRoute, push} = useRouter()

const active = ref(0)
const activeNames = ref(['1', '2'])

const areaTreeOptions = ref([])

// 表格的数据
const list = ref([])

const emit = defineEmits(['change','submit'])
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
const kuaijie = () => {
  console.log(11)
}
const handleChange = () => {
  console.log(11)
}

const next = () => {
  if (unref(formRef)?.validate()) {
    console.log(formData)
    emit('submit', {data:formData})
  }
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
