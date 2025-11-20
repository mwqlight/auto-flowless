<template>
  <div class="price-lab-container">
    <div class="search-bar">
      <el-input
        v-model="searchKeyword"
        placeholder="请输入商品名称"
        style="width: 300px; margin-right: 10px;"
        @keyup.enter="handleSearch"
      >
        <template #append>
          <el-button type="primary" @click="handleSearch">
            <el-icon><Search /></el-icon> 搜索
          </el-button>
        </template>
      </el-input>
    </div>

    <div class="result-section">
      <div class="result-header">
        <h3>比价结果</h3>
        <el-select v-model="sortBy" placeholder="排序" style="width: 150px;">
          <el-option label="价格从低到高" value="price_asc" />
          <el-option label="价格从高到低" value="price_desc" />
          <el-option label="销量从高到低" value="sales_desc" />
        </el-select>
      </div>

      <el-table :data="productList" border style="width: 100%">
        <el-table-column prop="productName" label="商品名称" width="300" />
        <el-table-column prop="platform" label="平台" width="100" />
        <el-table-column prop="price" label="价格" width="120">
          <template #default="scope">
            <span class="price-highlight" v-if="scope.row.price === lowestPrice">¥{{ scope.row.price }}</span>
            <span v-else>¥{{ scope.row.price }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="shopName" label="店铺" width="150" />
        <el-table-column prop="discount" label="优惠" width="150" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" size="small" @click="viewDetail(scope.row)">
              <el-icon><InfoFilled /></el-icon> 详情
            </el-button>
            <el-button type="success" size="small" @click="addMonitor(scope.row)">
              <el-icon><StarFilled /></el-icon> 监控
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="chart-section" v-if="showChart">
      <h3>价格历史趋势</h3>
      <div id="priceChart" style="width: 100%; height: 400px;"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { Search, InfoFilled, StarFilled } from '@element-plus/icons-vue'
import * as echarts from 'echarts'
import { compareProducts, getPriceHistory, addMonitor } from '@/api/price-lab'
import { ElMessage } from 'element-plus'

const searchKeyword = ref('')
const productList = ref([])
const sortBy = ref('')
const showChart = ref(false)
let chartInstance: echarts.ECharts | null = null

const handleSearch = () => {
  if (!searchKeyword.value) return
  
  // 调用API搜索商品
  compareProducts(searchKeyword.value)
    .then(response => {
      productList.value = response.data
      ElMessage.success('搜索成功')
    })
    .catch(error => {
      console.error('搜索失败:', error)
      ElMessage.error('搜索失败，请稍后重试')
    })
}

const lowestPrice = computed(() => {
  if (!productList.value.length) return 0
  return Math.min(...productList.value.map(item => item.price))
})

const viewDetail = (product: any) => {
  console.log('查看商品详情:', product)
  showChart.value = true
  initChart()
}

const addMonitor = (product: any) => {
  addMonitor({
    productId: product.id,
    targetPrice: product.price * 0.9 // 默认设置为当前价格的90%为目标价
  })
    .then(response => {
      ElMessage.success('已添加到价格监控')
    })
    .catch(error => {
      console.error('添加监控失败:', error)
      ElMessage.error('添加监控失败，请稍后重试')
    })
}

const initChart = () => {
  const chartDom = document.getElementById('priceChart')
  if (chartDom) {
    chartInstance = echarts.init(chartDom)
    const option: echarts.EChartsOption = {
      title: {
        text: '商品价格历史趋势',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: ['淘宝', '京东', '拼多多'],
        bottom: 10
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '15%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '淘宝',
          type: 'line',
          data: [100, 102, 98, 105, 103, 99, 101]
        },
        {
          name: '京东',
          type: 'line',
          data: [95, 97, 93, 100, 98, 94, 96]
        },
        {
          name: '拼多多',
          type: 'line',
          data: [88, 90, 85, 92, 90, 86, 88]
        }
      ]
    }
    chartInstance.setOption(option)
  }
}

onMounted(() => {
  window.addEventListener('resize', () => {
    chartInstance?.resize()
  })
})

onUnmounted(() => {
  chartInstance?.dispose()
  window.removeEventListener('resize', () => {
    chartInstance?.resize()
  })
})
</script>

<style scoped>
.price-lab-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: 100vh;
}

.search-bar {
  margin-bottom: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.result-section {
  margin-bottom: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.result-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.result-header h3 {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.chart-section {
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.chart-section h3 {
  margin: 0 0 20px 0;
  font-size: 18px;
  font-weight: 600;
}

.price-highlight {
  color: #f56c6c;
  font-weight: 600;
}
</style>
