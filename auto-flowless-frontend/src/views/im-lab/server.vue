<template>
  <div class="im-lab-server">
    <h2>IM服务器端</h2>
    <div class="server-content">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <span>服务器状态</span>
          </div>
        </template>
        <div class="status-info">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="status-item">
                <span class="label">服务器端口:</span>
                <span class="value">{{ serverPort }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="status-item">
                <span class="label">集群数量:</span>
                <span class="value">{{ clusterSize }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="status-item">
                <span class="label">连接数:</span>
                <span class="value">{{ connectionCount }}</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>服务器配置</span>
          </div>
        </template>
        <el-form :model="serverConfig" label-width="120px">
          <el-form-item label="服务器端口">
            <el-input v-model="serverConfig.port" placeholder="请输入服务器端口" />
          </el-form-item>
          <el-form-item label="集群数量">
            <el-input-number v-model="serverConfig.clusterSize" :min="1" :max="10" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveServerConfig">保存配置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>客户端列表</span>
          </div>
        </template>
        <el-table :data="clientList" style="width: 100%">
          <el-table-column prop="id" label="ID" width="80" />
          <el-table-column prop="clientId" label="客户端ID" width="120" />
          <el-table-column prop="email" label="邮箱" width="180" />
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <el-tag :type="scope.row.status === 'online' ? 'success' : 'danger'">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="lastActive" label="最后活跃时间" />
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

const serverPort = ref(8888)
const clusterSize = ref(1)
const connectionCount = ref(0)

const serverConfig = reactive({
  port: 8888,
  clusterSize: 1
})

const clientList = ref([
  { id: 1, clientId: 'client-001', email: 'user1@example.com', status: 'online', lastActive: '2023-05-20 14:30:00' },
  { id: 2, clientId: 'client-002', email: 'user2@example.com', status: 'offline', lastActive: '2023-05-20 14:25:00' }
])

const saveServerConfig = () => {
  // TODO: 保存服务器配置
  serverPort.value = serverConfig.port
  clusterSize.value = serverConfig.clusterSize
  ElMessage.success('配置保存成功')
}
</script>

<style scoped>
.im-lab-server {
  padding: 20px;
}

.server-content {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
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