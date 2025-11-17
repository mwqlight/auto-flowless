<template>
  <div class="code-generate-container">
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>代码生成配置</span>
        </div>
      </template>
      <el-form :model="form" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="数据库类型">
              <el-select v-model="form.dbType" placeholder="请选择数据库类型">
                <el-option label="MySQL" value="MySQL" />
                <!-- 后续可以添加更多数据库类型 -->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="数据库URL">
              <el-input v-model="form.dbUrl" placeholder="请输入数据库URL" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户名">
              <el-input v-model="form.dbUsername" placeholder="请输入用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码">
              <el-input v-model="form.dbPassword" type="password" placeholder="请输入密码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="表选择">
          <el-button type="primary" @click="fetchTables" :loading="fetchingTables">获取表列表</el-button>
          <el-checkbox-group v-model="form.tableNames" style="margin-left: 20px;">
            <el-checkbox v-for="table in tables" :key="table" :label="table">
              {{ table }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-divider />
        <el-form-item label="实体包名">
          <el-input v-model="form.entityPackage" placeholder="请输入实体包名" />
        </el-form-item>
        <el-form-item label="Mapper包名">
          <el-input v-model="form.mapperPackage" placeholder="请输入Mapper包名" />
        </el-form-item>
        <el-form-item label="Service包名">
          <el-input v-model="form.servicePackage" placeholder="请输入Service包名" />
        </el-form-item>
        <el-form-item label="Controller包名">
          <el-input v-model="form.controllerPackage" placeholder="请输入Controller包名" />
        </el-form-item>
        <el-form-item label="输出路径">
          <el-input v-model="form.outputPath" placeholder="请输入输出路径" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="generateCode" :loading="generating">生成代码</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { generateCodeApi, getTableNamesApi } from '@/api/silab'

const form = ref({
  dbType: 'MySQL',
  dbUrl: '',
  dbUsername: '',
  dbPassword: '',
  tableNames: [] as string[],
  entityPackage: '',
  mapperPackage: '',
  servicePackage: '',
  controllerPackage: '',
  outputPath: ''
})

const tables = ref<string[]>([])
const fetchingTables = ref(false)
const generating = ref(false)

const fetchTables = async () => {
  if (!form.value.dbUrl || !form.value.dbUsername || !form.value.dbPassword) {
    ElMessage.warning('请先填写数据库连接信息')
    return
  }
  fetchingTables.value = true
  try {
    const response = await getTableNamesApi(form.value.dbUrl, form.value.dbUsername, form.value.dbPassword)
    tables.value = response.data
    ElMessage.success('表列表获取成功')
  } catch (error) {
    ElMessage.error('表列表获取失败')
    console.error('Failed to fetch tables:', error)
  } finally {
    fetchingTables.value = false
  }
}

const generateCode = async () => {
  if (form.value.tableNames.length === 0) {
    ElMessage.warning('请至少选择一个表')
    return
  }
  generating.value = true
  try {
    await generateCodeApi(form.value)
    ElMessage.success('代码生成成功')
  } catch (error) {
    ElMessage.error('代码生成失败')
    console.error('Failed to generate code:', error)
  } finally {
    generating.value = false
  }
}

const resetForm = () => {
  form.value = {
    dbType: 'MySQL',
    dbUrl: '',
    dbUsername: '',
    dbPassword: '',
    tableNames: [],
    entityPackage: '',
    mapperPackage: '',
    servicePackage: '',
    controllerPackage: '',
    outputPath: ''
  }
  tables.value = []
}
</script>

<style scoped>
.code-generate-container {
  padding: 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
