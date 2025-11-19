<template>
  <div class="alg-lab-container">
    <div class="alg-lab-header">
      <h2>算法实验室</h2>
      <el-button type="primary" @click="addAlgorithm">
        <el-icon-plus /> AI添加算法
      </el-button>
    </div>
    <div class="alg-lab-content">
      <div class="left-sidebar">
        <el-tree
          :data="categoryTree"
          :props="treeProps"
          @node-click="handleCategoryClick"
          default-expand-all
        ></el-tree>
      </div>
      <div class="right-content">
        <div class="alg-list-header">
          <h3>{{ selectedCategoryName || '请选择算法分类' }}</h3>
        </div>
        <el-card v-for="alg in algorithmList" :key="alg.id" shadow="hover" class="alg-card">
          <template #header>
            <div class="card-header">
              <h4>{{ alg.algName }}</h4>
              <el-tag type="success">{{ alg.timeComplexity }}</el-tag>
              <el-tag type="info">{{ alg.spaceComplexity }}</el-tag>
            </div>
          </template>
          <div class="card-content">
            <p class="alg-description">{{ alg.description || '暂无描述' }}</p>
            <el-button type="primary" size="small" @click="goToDetail(alg.id)">
              查看详情
            </el-button>
          </div>
        </el-card>
      </div>
    </div>
    <!-- AI添加算法对话框 -->
    <el-dialog
      v-model="addAlgDialogVisible"
      title="AI添加算法"
      width="600px"
      @close="resetAddAlgForm"
    >
      <el-form ref="addAlgForm" :model="addAlgForm" label-width="100px">
        <el-form-item label="算法名称" required>
          <el-input v-model="addAlgForm.algName" placeholder="请输入算法名称" />
        </el-form-item>
        <el-form-item label="算法分类" required>
          <el-select v-model="addAlgForm.categoryId" placeholder="请选择算法分类">
            <el-option
              v-for="category in allCategories"
              :key="category.id"
              :label="category.categoryName"
              :value="category.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="addAlgDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitAddAlg">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { AlgCategory, Algorithm } from '@/types/alg-lab'
import { getCategoryTree, getAlgsByCategoryId } from '@/api/alg-lab'

const router = useRouter()

// 分类树数据
const categoryTree = ref<AlgCategory[]>([])
// 算法列表
const algorithmList = ref<Algorithm[]>([])
// 选中的分类ID
const selectedCategoryId = ref<number | undefined>()
// 选中的分类名称
const selectedCategoryName = ref<string>('')
// AI添加算法对话框可见性
const addAlgDialogVisible = ref(false)
// AI添加算法表单
const addAlgForm = reactive({
  algName: '',
  categoryId: undefined
})

// 树结构配置
const treeProps = {
  children: 'children',
  label: 'categoryName'
}

// 所有分类（用于下拉选择）
const allCategories = ref<AlgCategory[]>([])

// 加载分类树
const loadCategoryTree = async () => {
  try {
    const response = await getCategoryTree()
    if (response.code === 200) {
      categoryTree.value = response.data
      // 扁平化分类用于下拉选择
      flattenCategories(categoryTree.value)
    }
  } catch (error) {
    ElMessage.error('加载分类失败')
  }
}

// 扁平化分类
const flattenCategories = (categories: AlgCategory[]) => {
  for (const category of categories) {
    allCategories.value.push(category)
    if (category.children && category.children.length > 0) {
      flattenCategories(category.children)
    }
  }
}

// 处理分类点击
const handleCategoryClick = async (data: AlgCategory) => {
  selectedCategoryId.value = data.id
  selectedCategoryName.value = data.categoryName
  try {
    const response = await getAlgsByCategoryId(data.id!)
    if (response.code === 200) {
      algorithmList.value = response.data
    }
  } catch (error) {
    ElMessage.error('加载算法列表失败')
  }
}

// 跳转到算法详情页
const goToDetail = (algId: number) => {
  router.push(`/alg-lab/detail/${algId}`)
}

// AI添加算法
const addAlgorithm = () => {
  addAlgDialogVisible.value = true
}

// 重置添加算法表单
const resetAddAlgForm = () => {
  addAlgForm.algName = ''
  addAlgForm.categoryId = undefined
}

// 提交添加算法
const submitAddAlg = () => {
  // TODO: 调用AI添加算法接口
  ElMessage.success('AI添加算法功能开发中...')
  addAlgDialogVisible.value = false
}

onMounted(() => {
  loadCategoryTree()
})
</script>

<style scoped>
.alg-lab-container {
  width: 100%;
  height: 100%;
  padding: 20px;
}

.alg-lab-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.alg-lab-content {
  display: flex;
  height: calc(100% - 80px);
}

.left-sidebar {
  width: 300px;
  border-right: 1px solid #e6e6e6;
  padding-right: 20px;
  overflow-y: auto;
}

.right-content {
  flex: 1;
  padding-left: 20px;
  overflow-y: auto;
}

.alg-list-header {
  margin-bottom: 20px;
}

.alg-card {
  margin-bottom: 20px;
  cursor: pointer;
  transition: transform 0.2s;
}

.alg-card:hover {
  transform: translateY(-2px);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-content {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.alg-description {
  flex: 1;
  margin-right: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>
