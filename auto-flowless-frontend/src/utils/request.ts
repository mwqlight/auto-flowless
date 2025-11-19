import axios from 'axios'

const request = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 添加token请求头
    const token = localStorage.getItem('accessToken')
    if (token) {
      config.headers.Authorization = 'Bearer ' + token
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response) => {
    const { data } = response
    return data
  },
  (error) => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

export default request