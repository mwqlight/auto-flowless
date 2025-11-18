<template>
  <div class="llm-upload-container">
    <div class="llm-upload-header">
      <h1>模型上传</h1>
      <p>上传大模型文件到系统中</p>
    </div>
    
    <el-upload
      class="llm-upload-component"
      drag
      action=""
      :auto-upload="false"
      :file-list="fileList"
      :on-change="handleFileChange"
      :accept=".pt,.pth,.bin,.model"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        将模型文件拖到此处，或<em>点击上传</em>
      </div>
      <template #tip>
        <div class="el-upload__tip">
          支持上传 .pt/.pth/.bin/.model 格式文件，单个文件不超过 10GB
        </div>
      </template>
    </el-upload>
    
    <div class="llm-upload-form">
      <el-form :model="form" label-width="120px">
        <el-form-item label="模型名称">
          <el-input v-model="form.modelName" placeholder="请输入模型名称" />
        </el-form-item>
        
        <el-form-item label="模型版本">
          <el-input v-model="form.modelVersion" placeholder="请输入模型版本" />
        </el-form-item>
        
        <el-form-item label="模型描述">
          <el-input
            v-model="form.modelDescription"
            type="textarea"
            :rows="3"
            placeholder="请输入模型描述"
          />
        </el-form-item>
        
        <el-form-item label="模型类型">
          <el-select v-model="form.modelType" placeholder="请选择模型类型">
            <el-option label="文本生成" value="text_generation" />
            <el-option label="文本分类" value="text_classification" />
            <el-option label="图像识别" value="image_recognition" />
            <el-option label="语音识别" value="speech_recognition" />
          </el-select>
        </el-form-item>
      </el-form>
      
      <div class="llm-upload-actions">
        <el-button type="primary" @click="handleUpload" :disabled="fileList.length === 0">
          开始上传
        </el-button>
        <el-button @click="handleCancel">取消</el-button>
      </div>
    </div>
    
    <el-progress
      v-if="uploadProgress > 0 && uploadProgress < 100"
      :percentage="uploadProgress"
      status="active"
      class="llm-upload-progress"
    />
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { UploadFilled } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

// 文件列表
const fileList = ref([])

// 表单数据
const form = reactive({
  modelName: '',
  modelVersion: '',
  modelDescription: '',
  modelType: ''
})

// 上传进度
const uploadProgress = ref(0)

// 文件变化处理
const handleFileChange = (file: any, fileList: any[]) => {
  // 限制只能上传一个文件
  if (fileList.length > 1) {
    fileList.shift()
  }
}

// 开始上传
const handleUpload = async () => {
  if (!form.modelName || !form.modelVersion || !form.modelType) {
    ElMessage.warning('请填写完整的模型信息')
    return
  }
  
  if (fileList.value.length === 0) {
    ElMessage.warning('请选择要上传的模型文件')
    return
  }
  
  try {
    // 模拟上传过程
    uploadProgress.value = 0
    const interval = setInterval(() => {
      uploadProgress.value += 10
      if (uploadProgress.value >= 100) {
        clearInterval(interval)
        uploadProgress.value = 0
        ElMessage.success('模型上传成功')
        fileList.value = []
        // 重置表单
        Object.assign(form, {
          modelName: '',
          modelVersion: '',
          modelDescription: '',
          modelType: ''
        })
      }
    }, 500)
  } catch (error) {
    ElMessage.error('模型上传失败：' + (error as Error).message)
  }
}

// 取消上传
const handleCancel = () => {
  fileList.value = []
  uploadProgress.value = 0
}
</script>

<style scoped>
.llm-upload-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.llm-upload-header {
  margin-bottom: 30px;
}

.llm-upload-component {
  margin-bottom: 30px;
}

.llm-upload-form {
  margin-bottom: 30px;
}

.llm-upload-actions {
  text-align: right;
  margin-top: 20px;
}

.llm-upload-progress {
  margin-top: 20px;
}
</style>