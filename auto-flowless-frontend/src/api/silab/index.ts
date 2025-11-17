import request from '@/utils/request'

// 生成代码
export function generateCodeApi(data: any) {
  return request({
    url: '/api/silab/code-generate/generate',
    method: 'post',
    data
  })
}

// 获取支持的数据库类型
export function getSupportedDatabasesApi() {
  return request({
    url: '/api/silab/code-generate/supported-databases',
    method: 'get'
  })
}

// 获取数据库表名列表
export function getTableNamesApi(dbUrl: string, username: string, password: string) {
  return request({
    url: '/api/silab/code-generate/table-names',
    method: 'post',
    params: {
      dbUrl,
      username,
      password
    }
  })
}

// AI生成代码
export function generateByAIApi(data: any) {
  return request({
    url: '/api/silab/ai-generate/generate',
    method: 'post',
    data
  })
}
