<template>
  <div class="im-lab-intro">
    <h2>IM实验室业务介绍</h2>
    <div class="intro-content">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <span>功能概述</span>
          </div>
        </template>
        <div class="content">
          <p>IM实验室是一个基于Netty的即时通讯系统，支持多实例服务器和客户端，提供安全可靠的即时通讯服务。</p>
          <p>主要功能特性：</p>
          <ul>
            <li>支持服务器集群模式</li>
            <li>客户端多实例管理</li>
            <li>基于邮箱的寻址机制</li>
            <li>RSA+AES加密通信</li>
            <li>完整的配置管理</li>
            <li>实时状态监控</li>
          </ul>
        </div>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>技术架构</span>
          </div>
        </template>
        <div class="content">
          <h3>后端技术</h3>
          <ul>
            <li>Netty - 高性能网络通信框架</li>
            <li>Spring Boot - 快速开发框架</li>
            <li>RSA - 非对称加密算法</li>
            <li>AES - 对称加密算法</li>
            <li>JSON - 数据序列化格式</li>
          </ul>
          <h3>前端技术</h3>
          <ul>
            <li>Vue 3 - 前端框架</li>
            <li>Element Plus - UI组件库</li>
            <li>TypeScript - 类型安全</li>
          </ul>
        </div>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>使用说明</span>
          </div>
        </template>
        <div class="content">
          <h3>服务器端使用</h3>
          <ol>
            <li>在配置管理页面设置服务器端口和集群数量</li>
            <li>启动服务器端服务</li>
            <li>在服务器端页面监控客户端连接</li>
          </ol>
          <h3>客户端使用</h3>
          <ol>
            <li>在配置管理页面设置服务器地址和端口</li>
            <li>添加客户端实例</li>
            <li>启动客户端实例并连接服务器</li>
          </ol>
        </div>
      </el-card>
      
      <el-card shadow="hover" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>指令说明</span>
          </div>
        </template>
        <div class="content">
          <el-table :data="commandList" style="width: 100%">
            <el-table-column prop="command" label="指令" width="150" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="example" label="示例" />
          </el-table>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const commandList = ref([
  { command: 'connect', description: '连接服务器', example: 'connect localhost 8888' },
  { command: 'disconnect', description: '断开连接', example: 'disconnect' },
  { command: 'send', description: '发送消息', example: 'send user@example.com "Hello World"' },
  { command: 'list', description: '列出客户端', example: 'list clients' },
  { command: 'status', description: '查看状态', example: 'status' },
  { command: 'quit', description: '退出客户端', example: 'quit' }
])
</script>

<style scoped>
.im-lab-intro {
  padding: 20px;
}

.intro-content {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
}

.content {
  line-height: 1.8;
}

.content h3 {
  margin: 20px 0 10px 0;
  font-size: 14px;
  font-weight: bold;
  color: #303133;
}

.content ul, .content ol {
  margin: 10px 0;
  padding-left: 20px;
}

.content li {
  margin: 5px 0;
}
</style>