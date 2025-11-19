import request from '@/utils/request'
import { AlgCategory, Algorithm, AlgImplementation } from '@/types/alg-lab'

/**
 * 获取算法分类树
 */
export const getCategoryTree = () => {
  return request<ApiResponse<AlgCategory[]>>({
    url: '/alg-lab/categories/tree',
    method: 'get'
  })
}

/**
 * 根据父ID获取子分类
 */
export const getChildrenByParentId = (parentId: number) => {
  return request<ApiResponse<AlgCategory[]>>({ 
    url: `/alg-lab/categories/children/${parentId}`,
    method: 'get'
  })
}

/**
 * 根据分类ID获取算法列表
 */
export const getAlgsByCategoryId = (categoryId: number) => {
  return request<ApiResponse<Algorithm[]>>({ 
    url: `/alg-lab/algorithms/${categoryId}`,
    method: 'get'
  })
}

/**
 * 根据算法ID获取算法详情
 */
export const getAlgDetail = (algId: number) => {
  return request<ApiResponse<Algorithm>>({ 
    url: `/alg-lab/algorithm/${algId}`,
    method: 'get'
  })
}

/**
 * 根据算法ID获取实现列表
 */
export const getImplsByAlgId = (algId: number) => {
  return request<ApiResponse<AlgImplementation[]>>({ 
    url: `/alg-lab/implementations/${algId}`,
    method: 'get'
  })
}

/**
 * 根据实现ID获取实现详情
 */
export const getImplDetail = (implId: number) => {
  return request<ApiResponse<AlgImplementation>>({ 
    url: `/alg-lab/implementation/${implId}`,
    method: 'get'
  })
}

/**
 * AI添加算法
 */
export const addAlgByAI = (data: { algName: string; categoryId?: number }) => {
  return request<ApiResponse<Algorithm>>({ 
    url: '/alg-lab/ai/add',
    method: 'post',
    data
  })
}
