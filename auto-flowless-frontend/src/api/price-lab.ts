import request from '@/utils/request'

// 搜索商品并比价
export function compareProducts(keyword: string) {
  return request({
    url: '/api/price-lab/compare',
    method: 'get',
    params: { keyword }
  })
}

// 获取商品价格历史
export function getPriceHistory(productId: number) {
  return request({
    url: `/api/price-lab/price-history/${productId}`,
    method: 'get'
  })
}

// 添加商品到价格监控
export function addMonitor(data: any) {
  return request({
    url: '/api/price-lab/monitor',
    method: 'post',
    data
  })
}

// 获取商品信息
export function getProduct(productId: number) {
  return request({
    url: `/api/price-lab/product/${productId}`,
    method: 'get'
  })
}
