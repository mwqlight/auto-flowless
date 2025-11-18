<template>
  <div class="im-lab-client">
    <h2>IM客户端</h2>
    <div class="client-content">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <span>客户端配置</span>
          </div>
        </template>
        <el-form :model="clientConfig" label-width="120px">
          <el-form-item label="服务器地址">
            <el-input v-model="clientConfig.serverAddr" placeholder="请输入服务器地址" />
          </el-form-item>
          <el-form-item label="服务器端口">
            <el-input v-model="clientConfig.serverPort" placeholder="请输入服务器端口" />
          </el-form-item>
          <el-form-item label="客户端端口">
            <el-input v-model="clientConfig.clientPort" placeholder="请输入客户端端口" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="connectServer">连接服务器</el-button>
            <el-button @click="disconnectServer">断开连接</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>客户端状态</span>
          </div>
        </template>
        <div class="status-info">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="status-item">
                <span class="label">连接状态:</span>
                <el-tag :type="connected ? 'success' : 'danger'">
                  {{ connected ? '已连接' : '未连接' }}
                </el-tag>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="status-item">
                <span class="label">当前实例:</span>
                <span class="value">{{ currentInstance }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="status-item">
                <span class="label">消息总数:</span>
                <span class="value">{{ messageCount }}</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>客户端实例</span>
            <el-button type="primary" size="small" @click="addClientInstance">添加实例</el-button>
          </div>
        </template>
        <el-table :data="clientInstances" style="width: 100%">
          <el-table-column prop="id" label="ID" width="80" />
          <el-table-column prop="instanceId" label="实例ID" width="120" />
          <el-table-column prop="port" label="端口" width="100" />
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <el-tag :type="scope.row.status === 'running' ? 'success' : 'danger'">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template #default="scope">
              <el-button size="small" @click="startInstance(scope.row)">启动</el-button>
              <el-button size="small" @click="stopInstance(scope.row)">停止</el-button>
              <el-button size="small" type="danger" @click="removeInstance(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

const connected = ref(false)
const currentInstance = ref('client-001')
const messageCount = ref(0)

const clientConfig = reactive({
  serverAddr: 'localhost',
  serverPort: '8888',
  clientPort: '9999'
})

const clientInstances = ref([
  { id: 1, instanceId: 'client-001', port: '9999', status: 'running' },
  { id: 2, instanceId: 'client-002', port: '10000', status: 'stopped' }
])

const connectServer = () => {
  connected.value = true
  ElMessage.success('连接服务器成功')
}

const disconnectServer = () => {
  connected.value = false
  ElMessage.info('已断开服务器连接')
}

const addClientInstance = () => {
  const newId = clientInstances.value.length + 1
  clientInstances.value.push({
    id: newId,
    instanceId: `client-00${newId}`,
    port: `${9998 + newId}`,
    status: 'stopped'
  })
  ElMessage.success('添加客户端实例成功')
}

const startInstance = (instance: any) => {
  instance.status = 'running'
  ElMessage.success('启动实例成功')
}

const stopInstance = (instance: any) => {
  instance.status = 'stopped'
  ElMessage.info('停止实例成功')
}

const removeInstance = (instance: any) => {
  const index = clientInstances.value.findIndex(item => item.id === instance.id)
  if (index !== -1) {
    clientInstances.value.splice(index, 1)
    ElMessage.success('删除实例成功')
  }
}
</script>

<style scoped>
.im-lab-client {
  padding: 20px;
}

.client-content {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.status-info {
  padding: 20px 0;
}

.status-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
}

.label {
  font-weight: 500;
  margin-right: 10px;
  color: #606266;
}

.value {
  font-size: 18px;
  font-weight: bold;
  color: #303133;
}
</style>