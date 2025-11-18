<template>
  <div class="im-lab-config">
    <h2>IM配置管理</h2>
    <div class="config-content">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <span>系统配置</span>
          </div>
        </template>
        <el-form :model="systemConfig" label-width="120px">
          <el-form-item label="运行模式">
            <el-select v-model="systemConfig.mode" placeholder="请选择运行模式">
              <el-option label="仅服务器" value="server" />
              <el-option label="仅客户端" value="client" />
              <el-option label="服务器和客户端" value="both" />
            </el-select>
          </el-form-item>
          <el-form-item label="RSA公钥">
            <el-input v-model="systemConfig.rsaPublicKey" type="textarea" :rows="5" placeholder="请输入RSA公钥" />
          </el-form-item>
          <el-form-item label="RSA私钥">
            <el-input v-model="systemConfig.rsaPrivateKey" type="textarea" :rows="5" placeholder="请输入RSA私钥" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="generateRsaKey">生成RSA密钥对</el-button>
            <el-button type="primary" @click="saveSystemConfig">保存配置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>集群配置</span>
          </div>
        </template>
        <el-form :model="clusterConfig" label-width="120px">
          <el-form-item label="集群数量">
            <el-input-number v-model="clusterConfig.clusterSize" :min="1" :max="10" />
          </el-form-item>
          <el-form-item label="负载均衡策略">
            <el-select v-model="clusterConfig.loadBalance" placeholder="请选择负载均衡策略">
              <el-option label="轮询" value="round-robin" />
              <el-option label="随机" value="random" />
              <el-option label="最小连接" value="least-connection" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveClusterConfig">保存配置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>日志配置</span>
          </div>
        </template>
        <el-form :model="logConfig" label-width="120px">
          <el-form-item label="日志级别">
            <el-select v-model="logConfig.level" placeholder="请选择日志级别">
              <el-option label="DEBUG" value="debug" />
              <el-option label="INFO" value="info" />
              <el-option label="WARN" value="warn" />
              <el-option label="ERROR" value="error" />
            </el-select>
          </el-form-item>
          <el-form-item label="日志文件路径">
            <el-input v-model="logConfig.path" placeholder="请输入日志文件路径" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveLogConfig">保存配置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

const systemConfig = reactive({
  mode: 'both',
  rsaPublicKey: '',
  rsaPrivateKey: ''
})

const clusterConfig = reactive({
  clusterSize: 1,
  loadBalance: 'round-robin'
})

const logConfig = reactive({
  level: 'info',
  path: '/logs/im-lab.log'
})

const generateRsaKey = () => {
  // TODO: 生成RSA密钥对
  systemConfig.rsaPublicKey = '-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx1z9z6vz6vz6vz6vz6v\n...\n-----END PUBLIC KEY-----'
  systemConfig.rsaPrivateKey = '-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHXP3Pr8+r8+r8\n...\n-----END PRIVATE KEY-----'
  ElMessage.success('生成RSA密钥对成功')
}

const saveSystemConfig = () => {
  // TODO: 保存系统配置
  ElMessage.success('系统配置保存成功')
}

const saveClusterConfig = () => {
  // TODO: 保存集群配置
  ElMessage.success('集群配置保存成功')
}

const saveLogConfig = () => {
  // TODO: 保存日志配置
  ElMessage.success('日志配置保存成功')
}
</script>

<style scoped>
.im-lab-config {
  padding: 20px;
}

.config-content {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
}
</style>