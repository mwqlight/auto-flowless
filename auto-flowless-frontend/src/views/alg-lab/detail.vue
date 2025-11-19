<template>
  <div class="alg-detail-container">
    <div class="alg-detail-header">
      <el-button type="primary" size="small" @click="goBack">
        <el-icon-back /> 返回
      </el-button>
      <h2>{{ algorithm.algName }}</h2>
    </div>
    <div class="alg-info">
      <el-descriptions :column="2" bordered>
        <el-descriptions-item label="算法名称">{{ algorithm.algName }}</el-descriptions-item>
        <el-descriptions-item label="算法分类">{{ categoryName }}</el-descriptions-item>
        <el-descriptions-item label="时间复杂度">{{ algorithm.timeComplexity }}</el-descriptions-item>
        <el-descriptions-item label="空间复杂度">{{ algorithm.spaceComplexity }}</el-descriptions-item>
        <el-descriptions-item label="算法描述" :span="2">{{ algorithm.description || '暂无描述' }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="alg-content">
      <div class="content-header">
        <h3>算法实现</h3>
        <el-select v-model="selectedImplId" placeholder="选择实现" @change="handleImplChange">
          <el-option
            v-for="impl in implementations"
            :key="impl.id"
            :label="impl.implName"
            :value="impl.id"
          ></el-option>
        </el-select>
      </div>
      <div class="content-body">
        <div class="animation-section" v-if="selectedImpl.animationJson">
          <h4>动画演示</h4>
          <div class="animation-container">
            <!-- TODO: 实现动画演示组件 -->
            <div class="animation-placeholder">动画演示开发中...</div>
          </div>
        </div>
        <div class="code-section">
          <h4>代码演示</h4>
          <el-card shadow="hover">
            <template #header>
              <div class="code-header">
                <span>{{ selectedImpl.implName }}</span>
                <el-tag>{{ selectedImpl.language }}</el-tag>
              </div>
            </template>
            <div class="code-content">
              <pre><code :class="`language-${selectedImpl.language}`">{{ selectedImpl.codeContent }}</code></pre>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Algorithm, AlgImplementation } from '@/types/alg-lab'
import { getAlgDetail, getImplsByAlgId } from '@/api/alg-lab'

const router = useRouter()
const route = useRoute()

// 算法详情
const algorithm = reactive<Algorithm>({
  id: undefined,
  algName: '',
  algCode: '',
  categoryId: undefined,
  timeComplexity: '',
  spaceComplexity: '',
  description: '',
  status: 1,
  sort: 0
})

// 算法分类名称
const categoryName = ref<string>('')

// 算法实现列表
const implementations = ref<AlgImplementation[]>([])

// 选中的实现ID
const selectedImplId = ref<number | undefined>()

// 选中的实现
const selectedImpl = reactive<AlgImplementation>({
  id: undefined,
  algId: undefined,
  implName: '',
  implCode: '',
  codeContent: '',
  animationJson: '',
  language: 'java',
  status: 1,
  sort: 0
})

// 返回上一页
const goBack = () => {
  router.go(-1)
}

// 加载算法详情
const loadAlgDetail = async (algId: number) => {
  try {
    const response = await getAlgDetail(algId)
    if (response.code === 200) {
      Object.assign(algorithm, response.data)
      // 加载算法实现
      loadImplementations(algId)
    }
  } catch (error) {
    ElMessage.error('加载算法详情失败')
  }
}

// 加载算法实现
const loadImplementations = async (algId: number) => {
  try {
    const response = await getImplsByAlgId(algId)
    if (response.code === 200) {
      implementations.value = response.data
      if (implementations.value.length > 0) {
        selectedImplId.value = implementations.value[0].id
        Object.assign(selectedImpl, implementations.value[0])
      }
    }
  } catch (error) {
    ElMessage.error('加载算法实现失败')
  }
}

// 处理实现选择变化
const handleImplChange = (implId: number) => {
  const impl = implementations.value.find(item => item.id === implId)
  if (impl) {
    Object.assign(selectedImpl, impl)
  }
}

onMounted(() => {
  const algId = parseInt(route.params.algId as string)
  loadAlgDetail(algId)
})
</script>

<style scoped>
.alg-detail-container {
  width: 100%;
  height: 100%;
  padding: 20px;
}

.alg-detail-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.alg-detail-header h2 {
  margin-left: 20px;
  margin-bottom: 0;
}

.alg-info {
  margin-bottom: 30px;
}

.alg-content {
  margin-top: 20px;
}

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.content-body {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.animation-section {
  margin-bottom: 30px;
}

.animation-container {
  width: 100%;
  height: 400px;
  border: 1px solid #e6e6e6;
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f7fa;
}

.animation-placeholder {
  color: #909399;
  font-size: 18px;
}

.code-section {
  margin-bottom: 30px;
}

.code-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.code-content {
  max-height: 500px;
  overflow-y: auto;
}

pre {
  margin: 0;
  padding: 15px;
  background-color: #f5f7fa;
  border-radius: 4px;
  font-family: 'Courier New', Courier, monospace;
  font-size: 14px;
  line-height: 1.6;
}

code {
  display: block;
}
</style>
