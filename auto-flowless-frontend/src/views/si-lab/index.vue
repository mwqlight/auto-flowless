<template>
  <div class="si-lab-container">
    <h1>业务实验室</h1>
    <el-tabs v-model="activeTab" type="card">
      <el-tab-pane label="代码生成" name="code-generate">
        <code-generate></code-generate>
      </el-tab-pane>
      <el-tab-pane label="AI智能生成" name="ai-generate">
        <ai-generate></ai-generate>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import CodeGenerate from './CodeGenerate.vue'
import AiGenerate from './AiGenerate.vue'

const activeTab = ref('code-generate')
</script>

<style scoped>
.si-lab-container {
  padding: 20px;
}
</style>
