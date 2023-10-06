import request from '@/config/axios'
import {CommonStatusEnum} from "@/utils/constants";


export interface AuthTenantVO extends TenantVO {
  threeInOne: boolean,
  uscc: string,
  bln: string,
  blnFile: string,
  unitCode: string,
  unitCodeFile: string,
  taxId: string,
  taxIdFile: string,
  industry: string,
  industryText: string,
  registrationDate: Date,
  registeredCapital: string,
  registeredProvince: string,
  registeredCity: string,
  registeredAddress: string,
  corporate: string,
  corporateIdCard: string,
  corporateIdCardFileFront: string,
  corporateIdCardFileBack: string,
  enterpriseNature: string,
  contactIdCard: string,
  contactAuthorizeFile: string,
  contactIdCardFileFront: string,
  contactIdCardFileBack: string,
}

export interface TenantVO {
  id: number
  name: string
  contactName: string
  contactMobile: string
  status: number
  domain: string
  packageId: number
  username: string
  password: string
  expireTime: Date
  accountCount: number
  createTime: Date
}

export interface TenantPageReqVO extends PageParam {
  name?: string
  contactName?: string
  contactMobile?: string
  status?: number
  createTime?: Date[]
}

export interface TenantExportReqVO {
  name?: string
  contactName?: string
  contactMobile?: string
  status?: number
  createTime?: Date[]
}

// 查询租户列表
export const getTenantPage = (params: TenantPageReqVO) => {
  return request.get({ url: '/system/tenant/page', params })
}

// 查询租户详情
export const getTenant = (id: number) => {
  return request.get({ url: '/system/tenant/get?id=' + id })
}


// 租户开户创建
export const authTenantCreate = (data: AuthTenantVO) => {
  return request.post({ url: '/system/tenant/auth-create', data })
}

// 租户开户更新
export const authTenantUpdate = (data: AuthTenantVO) => {
  return request.post({ url: '/system/tenant/auth-update', data })
}

// 新增租户
export const createTenant = (data: TenantVO) => {
  return request.post({ url: '/system/tenant/create', data })
}

// 修改租户
export const updateTenant = (data: TenantVO) => {
  return request.put({ url: '/system/tenant/update', data })
}

// 删除租户
export const deleteTenant = (id: number) => {
  return request.delete({ url: '/system/tenant/delete?id=' + id })
}

// 导出租户
export const exportTenant = (params: TenantExportReqVO) => {
  return request.download({ url: '/system/tenant/export-excel', params })
}
