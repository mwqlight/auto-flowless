import request from '@/utils/request'

// 查询客户列表
export function getCustomerList(params: any) {
  return request({
    url: '/crm/customer/list',
    method: 'get',
    params
  })
}

// 查询客户详细
export function getCustomer(id: number) {
  return request({
    url: '/crm/customer/' + id,
    method: 'get'
  })
}

// 新增客户
export function addCustomer(data: any) {
  return request({
    url: '/crm/customer',
    method: 'post',
    data
  })
}

// 修改客户
export function updateCustomer(data: any) {
  return request({
    url: '/crm/customer',
    method: 'put',
    data
  })
}

// 删除客户
export function delCustomer(id: number) {
  return request({
    url: '/crm/customer/' + id,
    method: 'delete'
  })
}

// 批量删除客户
export function delCustomerByIds(ids: Array<number>) {
  return request({
    url: '/crm/customer/batch',
    method: 'delete',
    data: ids
  })
}

// 自动回收超期未跟进客户
export function autoRecycleOverdueCustomers() {
  return request({
    url: '/crm/customer/autoRecycle',
    method: 'post'
  })
}

// 获取客户等级选项
export function getCustomerLevelOptions() {
  return request({
    url: '/crm/customer/levelOptions',
    method: 'get'
  })
}

// 获取行业类型选项
export function getIndustryTypeOptions() {
  return request({
    url: '/crm/customer/industryOptions',
    method: 'get'
  })
}

// 获取企业规模选项
export function getEnterpriseScaleOptions() {
  return request({
    url: '/crm/customer/scaleOptions',
    method: 'get'
  })
}