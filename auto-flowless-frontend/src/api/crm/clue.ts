import request from '@/utils/request'
import { AxiosPromise } from 'axios'
import { CrmClue } from '@/types/crm/clue'

/**
 * 查询线索信息列表
 * @param params 查询参数
 * @returns 线索信息列表
 */
export function getClueList(params: CrmClue): AxiosPromise<CrmClue[]> {
  return request({
    url: '/crm/clue/list',
    method: 'get',
    params
  })
}

/**
 * 获取线索信息详细信息
 * @param id 线索信息ID
 * @returns 线索信息
 */
export function getClueInfo(id: string): AxiosPromise<CrmClue> {
  return request({
    url: `/crm/clue/${id}`,
    method: 'get'
  })
}

/**
 * 新增线索信息
 * @param data 线索信息
 * @returns 结果
 */
export function addClue(data: CrmClue): AxiosPromise<number> {
  return request({
    url: '/crm/clue',
    method: 'post',
    data
  })
}

/**
 * 修改线索信息
 * @param data 线索信息
 * @returns 结果
 */
export function updateClue(data: CrmClue): AxiosPromise<number> {
  return request({
    url: '/crm/clue',
    method: 'put',
    data
  })
}

/**
 * 删除线索信息
 * @param id 线索信息ID
 * @returns 结果
 */
export function deleteClue(id: string): AxiosPromise<number> {
  return request({
    url: `/crm/clue/${id}`,
    method: 'delete'
  })
}

/**
 * 批量删除线索信息
 * @param ids 需要删除的数据ID
 * @returns 结果
 */
export function deleteClueByIds(ids: string): AxiosPromise<number> {
  return request({
    url: `/crm/clue/batch?ids=${ids}`,
    method: 'delete'
  })
}

/**
 * 自动回收超期未跟进的线索
 * @returns 结果
 */
export function autoRecycleOverdueClues(): AxiosPromise<void> {
  return request({
    url: '/crm/clue/autoRecycle',
    method: 'post'
  })
}
