import request from '@/utils/request'
import { AxiosPromise } from 'axios'
import { CrmOpportunity } from '@/types/crm/opportunity'

/**
 * 查询商机信息列表
 * @param params 查询参数
 * @returns 商机信息列表
 */
export function getOpportunityList(params: CrmOpportunity): AxiosPromise<CrmOpportunity[]> {
  return request({
    url: '/crm/opportunity/list',
    method: 'get',
    params
  })
}

/**
 * 获取商机信息详细信息
 * @param id 商机信息ID
 * @returns 商机信息
 */
export function getOpportunityInfo(id: string): AxiosPromise<CrmOpportunity> {
  return request({
    url: `/crm/opportunity/${id}`,
    method: 'get'
  })
}

/**
 * 新增商机信息
 * @param data 商机信息
 * @returns 结果
 */
export function addOpportunity(data: CrmOpportunity): AxiosPromise<number> {
  return request({
    url: '/crm/opportunity',
    method: 'post',
    data
  })
}

/**
 * 修改商机信息
 * @param data 商机信息
 * @returns 结果
 */
export function updateOpportunity(data: CrmOpportunity): AxiosPromise<number> {
  return request({
    url: '/crm/opportunity',
    method: 'put',
    data
  })
}

/**
 * 删除商机信息
 * @param id 商机信息ID
 * @returns 结果
 */
export function deleteOpportunity(id: string): AxiosPromise<number> {
  return request({
    url: `/crm/opportunity/${id}`,
    method: 'delete'
  })
}

/**
 * 批量删除商机信息
 * @param ids 需要删除的数据ID
 * @returns 结果
 */
export function deleteOpportunityByIds(ids: string): AxiosPromise<number> {
  return request({
    url: `/crm/opportunity/batch?ids=${ids}`,
    method: 'delete'
  })
}

/**
 * 自动回收超期未跟进的商机
 * @returns 结果
 */
export function autoRecycleOverdueOpportunities(): AxiosPromise<void> {
  return request({
    url: '/crm/opportunity/autoRecycle',
    method: 'post'
  })
}
