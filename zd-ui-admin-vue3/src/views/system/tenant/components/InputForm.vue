<template>
  <el-main>
    <el-form
        ref="formRef"
        v-loading="formLoading"
        :model="formData"
        :rules="formRules"
        label-width="140px"
        @submit.native.prevent
    >
      <el-collapse v-model="activeNames">
        <el-collapse-item title="Consistency" name="1" @click.stop>
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
                        v-model="countySelect"
                        placeholder="请选择"
                        :options="areaTreeOptions"
                        :props="{ value: 'id',label: 'name'}"
                        @change="handleChange"
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
                <el-input v-model="formData.contact_id_card" placeholder="请填写身份证号码"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </el-form>
  </el-main>
  <el-footer>
    <el-card class="account-auth__form-toolbar text-center mt-10px" shadow="always">

      <el-button style="margin-top: 12px"  @click="prev">取消</el-button>
      <el-button style="margin-top: 12px" type="primary" @click="next">保存，下一步</el-button>
    </el-card>
  </el-footer>
</template>
<script setup lang="tsx">

import {CACHE_KEY, useCache} from "@/hooks/web/useCache";

defineOptions({name: 'InputForm'})
import { ElMessageBox } from 'element-plus'
import {ref} from 'vue'
import {reactive} from 'vue'
import {DICT_TYPE, getIntDictOptions, getStrDictOptions} from "@/utils/dict";
import {getAreaTree} from '@/api/system/area'
import {propTypes} from "@/utils/propTypes";
import {useUserStore} from "@/store/modules/user";
import {useTagsViewStore} from "@/store/modules/tagsView";

const props = defineProps({
  value: propTypes.object.def({})
})
const emit = defineEmits(['change', 'submit','prev'])
const {currentRoute, push,replace} = useRouter()

const active = ref(0)
const activeNames = ref(['1', '2'])

const areaTreeOptions = ref([])
const list = ref([])
const message = useMessage()

const { t } = useI18n()

const userStore = useUserStore()

const tagsViewStore = useTagsViewStore()


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
/**
 * 获得数据列表
 */
const getList = async () => {
  areaTreeOptions.value = await getAreaTree()
}

const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formData = props.value

const formRules = reactive({
  name: [{required: true, message: '此项必填', trigger: 'blur'}],
  three_in_one: [{required: true, message: '此项必填', trigger: 'blur'}],
  uscc: [{required: true, message: '此项必填', trigger: 'blur'}],
  bln: [{required: true, message: '此项必填', trigger: 'blur'}],
  unit_code: [{required: true, message: '此项必填', trigger: 'blur'}],
  tax_id: [{required: true, message: '此项必填', trigger: 'blur'}],
  industry: [{required: true, message: '此项必填', trigger: 'blur'}],
  registration_date: [{required: true, message: '此项必填', trigger: 'blur'}],
  registered_capital: [{required: true, message: '此项必填', trigger: 'blur'}],
  registered_province: [{required: true, message: '此项必填', trigger: 'blur'}],
  registered_city: [{required: true, message: '此项必填', trigger: 'blur'}],
  registered_county: [{required: true, message: '此项必填', trigger: 'blur'}],
  registered_address: [{required: true, message: '此项必填', trigger: 'blur'}],
  corporate: [{required: true, message: '此项必填', trigger: 'blur'}],
  corporate_id_card: [{required: true, message: '此项必填', trigger: 'blur'}],
  enterprise_nature: [{required: true, message: '此项必填', trigger: 'blur'}],
  contact_name: [{required: true, message: '此项必填', trigger: 'blur'}],
  contact_mobile: [{required: true, message: '此项必填', trigger: 'blur'}],
  contact_id_card: [{required: true, message: '此项必填', trigger: 'blur'}]
})

const formRef = ref() // 表单 Ref
const kuaijie = () => {
  message.success("快捷注册")
}
const handleChange = (e) => {
  if (e){
    formData.registered_province=e[0]
    formData.registered_city=e[1]
    formData.registered_county=e[2]
  }
}
const countySelect = computed(() => {
  return [formData.registered_province,formData.registered_city,formData.registered_county]
})
const next = async () => {
  console.log('form', formData)
  const valid = await formRef.value.validate()
  if (!valid) return
  emit('submit', {data: formData})
}

const prev =  () => {
  loginOut()
}
/** 初始化 **/
onMounted(() => {
  getList()

})
</script>
<style lang="less" scoped>

</style>
