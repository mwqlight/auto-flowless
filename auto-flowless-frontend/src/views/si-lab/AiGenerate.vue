<template>
  <div class="ai-generate-container">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>AI智能生成</span>
        </div>
      </template>
      <el-form :model="form" label-width="120px">
        <el-form-item label="业务描述">
          <el-input
            v-model="form.businessDescription"
            type="textarea"
            :rows="5"
            placeholder="请输入业务描述，例如：创建一个用户管理系统，包含用户表、角色表、权限表"
          />
        </el-form-item>
        <el-form-item label="生成类型">
          <el-checkbox-group v-model="form.generateTypes">
            <el-checkbox label="entity" name="type">实体类</el-checkbox>
            <el-checkbox label="mapper" name="type">Mapper接口</el-checkbox>
            <el-checkbox label="service" name="type">Service类</el-checkbox>
            <el-checkbox label="controller" name="type">Controller类</el-checkbox>
            <el-checkbox label="swagger" name="type">Swagger文档</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="实体包名">
              <el-input v-model="form.entityPackage" placeholder="请输入实体包名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Mapper包名">
              <el-input v-model="form.mapperPackage" placeholder="请输入Mapper包名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="Service包名">
              <el-input v-model="form.servicePackage" placeholder="请输入Service包名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Controller包名">
              <el-input v-model="form.controllerPackage" placeholder="请输入Controller包名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="输出路径">
          <el-input v-model="form.outputPath" placeholder="请输入输出路径" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="generateByAI" :loading="generating">AI生成代码</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const form = ref({
  businessDescription: '',
  generateTypes: ['entity', 'mapper', 'service', 'controller', 'swagger'],
  entityPackage: '',
  mapperPackage: '',
  servicePackage: '',
  controllerPackage: '',
  outputPath: ''
})

const generating = ref(false)

const generateByAI = async () => {
  if (!form.value.businessDescription) {
    ElMessage.warning('请输入业务描述')
    return
  }
  if (form.value.generateTypes.length === 0) {
    ElMessage.warning('请至少选择一种生成类型')
    return
  }
  generating.value = true
  try {
    // TODO: 调用AI生成代码的API
    ElMessage.success('AI代码生成成功')
  } catch (error) {
    ElMessage.error('AI代码生成失败')
    console.error('Failed to generate code by AI:', error)
  } finally {
    generating.value = false
  }
}

const resetForm = () => {
  form.value = {
    businessDescription: '',
    generateTypes: ['entity', 'mapper', 'service', 'controller', 'swagger'],
    entityPackage: '',
    mapperPackage: '',
    servicePackage: '',
    controllerPackage: '',
    outputPath: ''
  }
}
</script>

<style scoped>
.ai-generate-container {
  padding: 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
