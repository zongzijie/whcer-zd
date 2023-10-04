<script setup lang="tsx">
import * as PostApi from "@/api/system/post";

defineOptions({name: 'TenantAuth'})
import {ref} from 'vue'

const {currentRoute, push} = useRouter()
import {Edit, Search, Upload} from '@element-plus/icons-vue'
import {InfoFilled} from '@element-plus/icons-vue'
import * as AreaApi from "@/api/system/area";
import InputForm from "@/views/system/tenant/components/InputForm.vue";
import UploadForm from "@/views/system/tenant/components/UploadForm.vue";
import CheckoutForm from "@/views/system/tenant/components/CheckoutForm.vue";
import {CommonStatusEnum} from "@/utils/constants";

const active = ref(1)

const submit=()=>{
  //TODO:WJJ 待补充持久化逻辑
  next()
}
const next = () => {
  if (active.value++ >= 2) active.value = 0
}
const back = () => {
  push({path: "/"})
}
const handleChange = (e) => {
  formData.value[e.label] = e.value;
  console.log(e)
}
</script>

<template>
  <el-page-header @back="back" class="ml10 mt10">
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
            <input-form @change="handleChange" @submit="submit"></input-form>
          </div>
          <div v-show="active==1" class="mt5 mr30 ml30">
            <upload-form @change="handleChange" @submit="submit"></upload-form>
          </div>
          <div v-show="active==2" class="mt5 mr30 ml30">
            <checkout-form @submit="submit"></checkout-form>
          </div>

      </el-container>
    </div>

  </el-scrollbar>
</template>

<style lang="less" scoped>


</style>
