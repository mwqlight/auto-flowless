<template>
  <div class="llm-evaluate-container">
    <div class="llm-evaluate-header">
      <h1>模型评估</h1>
      <p>评估已训练模型的性能</p>
    </div>
    
    <el-tabs v-model:activeName="activeTab" class="llm-evaluate-tabs">
      <el-tab-pane label="评估配置" name="config">
        <div class="llm-evaluate-form">
          <el-form :model="form" label-width="140px">
            <el-form-item label="选择模型">
              <el-select v-model="form.modelId" placeholder="请选择要评估的模型">
                <el-option
                  v-for="model in modelList"
                  :key="model.modelId"
                  :label="`${model.modelName} (${model.modelVersion})`"
                  :value="model.modelId"
                />
              </el-select>
            </el-form-item>
            
            <el-form-item label="评估数据">
              <el-upload
                class="llm-evaluate-upload"
                action=""
                :auto-upload="false"
                :file-list="dataFileList"
                :on-change="handleDataFileChange"
                accept=".txt,.csv,.json,.zip"
              >
                <el-button type="primary">
                  <el-icon><UploadFilled /></el-icon>
                  选择评估数据
                </el-button>
              </el-upload>
            </el-form-item>
            
            <el-form-item label="评估指标">
              <el-checkbox-group v-model="form.evaluateMetrics">
                <el-checkbox label="accuracy">准确率</el-checkbox>
                <el-checkbox label="precision">精确率</el-checkbox>
                <el-checkbox label="recall">召回率</el-checkbox>
                <el-checkbox label="f1">F1值</el-checkbox>
                <el-checkbox label="perplexity">困惑度</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            
            <el-form-item label="评估参数">
              <el-input
                v-model="form.evaluateParams"
                type="textarea"
                :rows="4"
                placeholder="请输入其他评估参数（JSON格式）"
              />
            </el-form-item>
            
            <el-form-item label="评估说明">
              <el-input
                v-model="form.evaluateDescription"
                type="textarea"
                :rows="2"
                placeholder="请输入评估说明"
              />
            </el-form-item>
          </el-form>
          
          <div class="llm-evaluate-actions">
            <el-button type="primary" @click="handleStartEvaluate" :disabled="!form.modelId">
              开始评估
            </el-button>
            <el-button @click="handleResetForm">重置</el-button>
          </div>
        </div>
      </el-tab-pane>
      
      <el-tab-pane label="评估结果" name="results">
        <div class="llm-evaluate-results">
          <el-table
            :data="evaluateResults"
            border
            stripe
            class="llm-evaluate-table"
          >
            <el-table-column prop="resultId" label="结果ID" width="150" />
            <el-table-column prop="modelName" label="模型名称" width="180" />
            <el-table-column prop="modelVersion" label="模型版本" width="120" />
            <el-table-column prop="evaluateStatus" label="评估状态" width="120">
              <template #default="scope">
                <el-tag
                  :type="scope.row.evaluateStatus === 'running' ? 'warning' : scope.row.evaluateStatus === 'success' ? 'success' : 'danger'"
                >
                  {{ scope.row.evaluateStatus === 'running' ? '评估中' : scope.row.evaluateStatus === 'success' ? '已完成' : '失败' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="accuracy" label="准确率" width="120" />
            <el-table-column prop="precision" label="精确率" width="120" />
            <el-table-column prop="recall" label="召回率" width="120" />
            <el-table-column prop="f1" label="F1值" width="120" />
            <el-table-column prop="perplexity" label="困惑度" width="120" />
            <el-table-column prop="evaluateTime" label="评估时间" width="180" />
            <el-table-column label="操作" width="150" fixed="right">
              <template #default="scope">
                <el-button
                  type="primary"
                  size="small"
                  @click="handleViewReport(scope.row)"
                  :disabled="scope.row.evaluateStatus === 'running'"
                >
                  查看报告
                </el-button>
                <el-button
                  type="danger"
                  size="small"
                  @click="handleDeleteResult(scope.row)"
                  :disabled="scope.row.evaluateStatus === 'running'"
                >
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { UploadFilled } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'

// 激活的标签页
const activeTab = ref('config')

// 表单数据
const form = reactive({
  modelId: '',
  evaluateMetrics: ['accuracy', 'precision', 'recall', 'f1'],
  evaluateParams: '{}',
  evaluateDescription: ''
})

// 评估数据文件列表
const dataFileList = ref([])

// 模型列表
const modelList = ref([
  {
    modelId: 'llm-20231001-001',
    modelName: 'ChatGPT',
    modelVersion: '3.5'
  },
  {
    modelId: 'llm-20231002-002',
    modelName: 'BERT',
    modelVersion: 'base'
  },
  {
    modelId: 'llm-20231003-003',
    modelName: 'ResNet',
    modelVersion: '50'
  }
])

// 评估结果列表
const evaluateResults = ref([
  {
    resultId: 'eval-20231005-001',
    modelName: 'ChatGPT',
    modelVersion: '3.5',
    evaluateStatus: 'success',
    accuracy: '89.2%',
    precision: '88.5%',
    recall: '87.8%',
    f1: '88.1%',
    perplexity: '12.5',
    evaluateTime: '2023-10-05 14:30:00'
  },
  {
    resultId: 'eval-20231004-002',
    modelName: 'BERT',
    modelVersion: 'base',
    evaluateStatus: 'success',
    accuracy: '92.5%',
    precision: '91.8%',
    recall: '92.1%',
    f1: '91.9%',
    perplexity: '',
    evaluateTime: '2023-10-04 16:45:00'
  },
  {
    resultId: 'eval-20231003-003',
    modelName: 'ResNet',
    modelVersion: '50',
    evaluateStatus: 'running',
    accuracy: '',
    precision: '',
    recall: '',
    f1: '',
    perplexity: '',
    evaluateTime: '2023-10-03 10:00:00'
  }
])

// 页面加载时获取模型列表
onMounted(() => {
  fetchModelList()
})

// 获取模型列表
const fetchModelList = () => {
  // 模拟API请求
  console.log('获取模型列表')
}

// 数据文件变化处理
const handleDataFileChange = (file: any, fileList: any[]) => {
  // 限制只能上传一个文件
  if (fileList.length > 1) {
    fileList.shift()
  }
}

// 开始评估
const handleStartEvaluate = async () => {
  if (!form.modelId) {
    ElMessage.warning('请选择要评估的模型')
    return
  }
  
  if (dataFileList.value.length === 0) {
    ElMessage.warning('请选择评估数据')
    return
  }
  
  try {
    ElMessageBox.confirm('确定要开始评估吗？', '评估确认', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    .then(() => {
      // 模拟评估启动
      console.log('开始评估模型:', form.modelId)
      ElMessage.success('评估任务已启动')
      // 切换到结果列表
      activeTab.value = 'results'
    })
  } catch (error) {
    ElMessage.error('评估启动失败：' + (error as Error).message)
  }
}

// 重置表单
const handleResetForm = () => {
  Object.assign(form, {
    modelId: '',
    evaluateMetrics: ['accuracy', 'precision', 'recall', 'f1'],
    evaluateParams: '{}',
    evaluateDescription: ''
  })
  dataFileList.value = []
}

// 查看评估报告
const handleViewReport = (row: any) => {
  console.log('查看评估报告:', row.resultId)
  ElMessage.info('查看评估报告功能待实现')
}

// 删除评估结果
const handleDeleteResult = (row: any) => {
  ElMessageBox.confirm('确定要删除该评估结果吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  .then(() => {
    // 模拟删除操作
    const index = evaluateResults.value.findIndex(item => item.resultId === row.resultId)
    if (index !== -1) {
      evaluateResults.value.splice(index, 1)
      ElMessage.success('评估结果已删除')
    }
  })
  .catch(() => {
    ElMessage.info('已取消删除')
  })
}
</script>

<style scoped>
.llm-evaluate-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.llm-evaluate-header {
  margin-bottom: 30px;
}

.llm-evaluate-tabs {
  margin-bottom: 20px;
}

.llm-evaluate-form {
  max-width: 800px;
}

.llm-evaluate-upload {
  margin-bottom: 20px;
}

.llm-evaluate-actions {
  text-align: right;
  margin-top: 20px;
}

.llm-evaluate-table {
  margin-bottom: 20px;
}
</style>