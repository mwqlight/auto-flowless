<template>
  <div class="llm-train-container">
    <div class="llm-train-header">
      <h1>模型训练</h1>
      <p>对已上传的模型进行训练</p>
    </div>
    
    <el-tabs v-model:activeName="activeTab" class="llm-train-tabs">
      <el-tab-pane label="训练配置" name="config">
        <div class="llm-train-form">
          <el-form :model="form" label-width="140px">
            <el-form-item label="选择模型">
              <el-select v-model="form.modelId" placeholder="请选择要训练的模型">
                <el-option
                  v-for="model in modelList"
                  :key="model.modelId"
                  :label="`${model.modelName} (${model.modelVersion})`"
                  :value="model.modelId"
                />
              </el-select>
            </el-form-item>
            
            <el-form-item label="训练数据">
              <el-upload
                class="llm-train-upload"
                action=""
                :auto-upload="false"
                :file-list="dataFileList"
                :on-change="handleDataFileChange"
                accept=".txt,.csv,.json,.zip"
              >
                <el-button type="primary">
                  <el-icon><UploadFilled /></el-icon>
                  选择训练数据
                </el-button>
              </el-upload>
            </el-form-item>
            
            <el-form-item label="训练 epoch">
              <el-input-number
                v-model="form.epochs"
                :min="1"
                :max="100"
                :step="1"
                placeholder="请输入训练 epoch"
              />
            </el-form-item>
            
            <el-form-item label="学习率">
              <el-input
                v-model="form.learningRate"
                type="number"
                :min="0.000001"
                :max="1"
                :step="0.000001"
                placeholder="请输入学习率"
              />
            </el-form-item>
            
            <el-form-item label="批次大小">
              <el-input-number
                v-model="form.batchSize"
                :min="1"
                :max="1024"
                :step="1"
                placeholder="请输入批次大小"
              />
            </el-form-item>
            
            <el-form-item label="训练参数">
              <el-input
                v-model="form.trainParams"
                type="textarea"
                :rows="4"
                placeholder="请输入其他训练参数（JSON格式）"
              />
            </el-form-item>
            
            <el-form-item label="训练说明">
              <el-input
                v-model="form.trainDescription"
                type="textarea"
                :rows="2"
                placeholder="请输入训练说明"
              />
            </el-form-item>
          </el-form>
          
          <div class="llm-train-actions">
            <el-button type="primary" @click="handleStartTrain" :disabled="!form.modelId">
              开始训练
            </el-button>
            <el-button @click="handleResetForm">重置</el-button>
          </div>
        </div>
      </el-tab-pane>
      
      <el-tab-pane label="训练任务" name="tasks">
        <div class="llm-train-tasks">
          <el-table
            :data="trainTasks"
            border
            stripe
            class="llm-train-table"
          >
            <el-table-column prop="taskId" label="任务ID" width="150" />
            <el-table-column prop="modelName" label="模型名称" width="180" />
            <el-table-column prop="modelVersion" label="模型版本" width="120" />
            <el-table-column prop="trainStatus" label="训练状态" width="120">
              <template #default="scope">
                <el-tag
                  :type="scope.row.trainStatus === 'running' ? 'warning' : scope.row.trainStatus === 'success' ? 'success' : 'danger'"
                >
                  {{ scope.row.trainStatus === 'running' ? '训练中' : scope.row.trainStatus === 'success' ? '已完成' : '失败' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="progress" label="训练进度" width="150">
              <template #default="scope">
                <el-progress
                  :percentage="scope.row.progress"
                  :status="scope.row.trainStatus === 'running' ? 'active' : scope.row.trainStatus === 'success' ? 'success' : 'exception'"
                  size="small"
                />
              </template>
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间" width="180" />
            <el-table-column prop="endTime" label="结束时间" width="180" />
            <el-table-column prop="accuracy" label="准确率" width="100" />
            <el-table-column label="操作" width="150" fixed="right">
              <template #default="scope">
                <el-button
                  type="primary"
                  size="small"
                  @click="handleViewLog(scope.row)"
                  :disabled="scope.row.trainStatus === 'running'"
                >
                  查看日志
                </el-button>
                <el-button
                  type="danger"
                  size="small"
                  @click="handleStopTrain(scope.row)"
                  :disabled="scope.row.trainStatus !== 'running'"
                >
                  停止
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
  epochs: 10,
  learningRate: 0.001,
  batchSize: 32,
  trainParams: '{}',
  trainDescription: ''
})

// 训练数据文件列表
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
  }
])

// 训练任务列表
const trainTasks = ref([
  {
    taskId: 'train-20231005-001',
    modelName: 'ChatGPT',
    modelVersion: '3.5',
    trainStatus: 'running',
    progress: 75,
    startTime: '2023-10-05 10:30:00',
    endTime: '',
    accuracy: ''
  },
  {
    taskId: 'train-20231004-002',
    modelName: 'BERT',
    modelVersion: 'base',
    trainStatus: 'success',
    progress: 100,
    startTime: '2023-10-04 14:00:00',
    endTime: '2023-10-04 18:30:00',
    accuracy: '92.5%'
  },
  {
    taskId: 'train-20231003-003',
    modelName: 'ResNet',
    modelVersion: '50',
    trainStatus: 'fail',
    progress: 60,
    startTime: '2023-10-03 09:00:00',
    endTime: '2023-10-03 11:30:00',
    accuracy: ''
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

// 开始训练
const handleStartTrain = async () => {
  if (!form.modelId) {
    ElMessage.warning('请选择要训练的模型')
    return
  }
  
  if (dataFileList.value.length === 0) {
    ElMessage.warning('请选择训练数据')
    return
  }
  
  try {
    ElMessageBox.confirm('确定要开始训练吗？', '训练确认', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    .then(() => {
      // 模拟训练启动
      console.log('开始训练模型:', form.modelId)
      ElMessage.success('训练任务已启动')
      // 切换到任务列表
      activeTab.value = 'tasks'
    })
  } catch (error) {
    ElMessage.error('训练启动失败：' + (error as Error).message)
  }
}

// 重置表单
const handleResetForm = () => {
  Object.assign(form, {
    modelId: '',
    epochs: 10,
    learningRate: 0.001,
    batchSize: 32,
    trainParams: '{}',
    trainDescription: ''
  })
  dataFileList.value = []
}

// 查看训练日志
const handleViewLog = (row: any) => {
  console.log('查看训练日志:', row.taskId)
  ElMessage.info('查看训练日志功能待实现')
}

// 停止训练
const handleStopTrain = (row: any) => {
  ElMessageBox.confirm('确定要停止训练吗？', '停止训练', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  .then(() => {
    console.log('停止训练:', row.taskId)
    ElMessage.success('训练已停止')
  })
  .catch(() => {
    ElMessage.info('已取消停止')
  })
}
</script>

<style scoped>
.llm-train-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.llm-train-header {
  margin-bottom: 30px;
}

.llm-train-tabs {
  margin-bottom: 20px;
}

.llm-train-form {
  max-width: 800px;
}

.llm-train-upload {
  margin-bottom: 20px;
}

.llm-train-actions {
  text-align: right;
  margin-top: 20px;
}

.llm-train-table {
  margin-bottom: 20px;
}
</style>