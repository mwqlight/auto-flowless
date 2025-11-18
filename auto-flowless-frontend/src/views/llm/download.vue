<template>
  <div class="llm-download-container">
    <div class="llm-download-header">
      <h1>模型下载</h1>
      <p>从系统中下载大模型文件</p>
    </div>
    
    <div class="llm-download-filters">
      <el-input
        v-model="keyword"
        placeholder="请输入模型名称或版本"
        prefix-icon="Search"
        clearable
        class="llm-download-search"
      />
      
      <el-select
        v-model="modelType"
        placeholder="请选择模型类型"
        clearable
        class="llm-download-select"
      >
        <el-option label="所有类型" value="" />
        <el-option label="文本生成" value="text_generation" />
        <el-option label="文本分类" value="text_classification" />
        <el-option label="图像识别" value="image_recognition" />
        <el-option label="语音识别" value="speech_recognition" />
      </el-select>
    </div>
    
    <el-table
      :data="modelList"
      border
      stripe
      class="llm-download-table"
    >
      <el-table-column prop="modelId" label="模型ID" width="120" />
      <el-table-column prop="modelName" label="模型名称" width="200" />
      <el-table-column prop="modelVersion" label="模型版本" width="120" />
      <el-table-column prop="modelType" label="模型类型" width="150" />
      <el-table-column prop="modelSize" label="模型大小" width="100" />
      <el-table-column prop="uploadTime" label="上传时间" width="180" />
      <el-table-column prop="status" label="状态" width="100" />
      <el-table-column prop="description" label="描述" min-width="200" />
      <el-table-column label="操作" width="120" fixed="right">
        <template #default="scope">
          <el-button
            type="primary"
            size="small"
            @click="handleDownload(scope.row)"
          >
            下载
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <div class="llm-download-pagination">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

// 搜索关键词
const keyword = ref('')

// 模型类型
const modelType = ref('')

// 当前页码
const currentPage = ref(1)

// 每页条数
const pageSize = ref(10)

// 总条数
const total = ref(50)

// 模型列表数据
const modelList = ref([
  {
    modelId: 'llm-20231001-001',
    modelName: 'ChatGPT',
    modelVersion: '3.5',
    modelType: 'text_generation',
    modelSize: '175B',
    uploadTime: '2023-10-01 14:30:00',
    status: '可用',
    description: '通用聊天模型'
  },
  {
    modelId: 'llm-20231002-002',
    modelName: 'BERT',
    modelVersion: 'base',
    modelType: 'text_classification',
    modelSize: '110M',
    uploadTime: '2023-10-02 09:15:00',
    status: '可用',
    description: '预训练语言模型'
  },
  {
    modelId: 'llm-20231003-003',
    modelName: 'ResNet',
    modelVersion: '50',
    modelType: 'image_recognition',
    modelSize: '97M',
    uploadTime: '2023-10-03 16:45:00',
    status: '可用',
    description: '图像识别模型'
  }
])

// 页面加载时获取模型列表
onMounted(() => {
  fetchModelList()
})

// 获取模型列表
const fetchModelList = () => {
  // 模拟API请求
  console.log('获取模型列表:', keyword.value, modelType.value, currentPage.value, pageSize.value)
}

// 每页条数变化
const handleSizeChange = (val: number) => {
  pageSize.value = val
  currentPage.value = 1
  fetchModelList()
}

// 当前页码变化
const handleCurrentChange = (val: number) => {
  currentPage.value = val
  fetchModelList()
}

// 下载模型
const handleDownload = (row: any) => {
  ElMessageBox.confirm(`确定要下载模型 ${row.modelName} (${row.modelVersion}) 吗？`, '下载确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  .then(() => {
    // 模拟下载过程
    console.log('开始下载模型:', row.modelId)
    ElMessage.success('模型下载任务已启动')
  })
  .catch(() => {
    ElMessage.info('已取消下载')
  })
}
</script>

<style scoped>
.llm-download-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.llm-download-header {
  margin-bottom: 30px;
}

.llm-download-filters {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.llm-download-search {
  width: 300px;
}

.llm-download-select {
  width: 200px;
}

.llm-download-table {
  margin-bottom: 20px;
}

.llm-download-pagination {
  text-align: right;
}
</style>