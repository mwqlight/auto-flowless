<template>
  <div class="clue-list-container">
    <el-page-header @back="$router.back" content="线索管理" />
    <el-card class="clue-list-card">
      <template #header>
        <div class="card-header">
          <span>线索列表</span>
          <el-button type="primary" @click="handleAdd">新增线索</el-button>
          <el-button @click="handleAutoRecycle">自动回收</el-button>
        </div>
      </template>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
        <el-form-item label="线索名称" prop="clueName">
          <el-input
            v-model="queryParams.clueName"
            placeholder="请输入线索名称"
            clearable
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="线索来源" prop="source">
          <el-input
            v-model="queryParams.source"
            placeholder="请输入线索来源"
            clearable
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="线索状态" prop="status">
          <el-select
            v-model="queryParams.status"
            placeholder="请选择线索状态"
            clearable
            style="width: 140px"
          >
            <el-option label="待跟进" value="0" />
            <el-option label="跟进中" value="1" />
            <el-option label="已转化" value="2" />
            <el-option label="已失效" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否公海池" prop="isPublicPool">
          <el-select
            v-model="queryParams.isPublicPool"
            placeholder="请选择是否公海池"
            clearable
            style="width: 140px"
          >
            <el-option label="是" value="true" />
            <el-option label="否" value="false" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="clueList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="线索名称" align="center" prop="clueName" show-overflow-tooltip />
        <el-table-column label="线索来源" align="center" prop="source" show-overflow-tooltip />
        <el-table-column label="线索状态" align="center" prop="status" show-overflow-tooltip>
          <template #default="scope">
            <el-tag :type="getTagType(scope.row.status)" size="small">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="客户经理" align="center" prop="accountManagerName" show-overflow-tooltip />
        <el-table-column label="最后跟进时间" align="center" prop="lastFollowUpTime" show-overflow-tooltip />
        <el-table-column label="是否公海池" align="center" prop="isPublicPool" show-overflow-tooltip>
          <template #default="scope">
            <el-tag :type="scope.row.isPublicPool ? 'success' : 'info'" size="small">
              {{ scope.row.isPublicPool ? '是' : '否' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" show-overflow-tooltip />
        <el-table-column label="更新时间" align="center" prop="updateTime" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              icon="Edit"
              @click="handleUpdate(scope.row)"
            >
              修改
            </el-button>
            <el-button
              size="small"
              type="danger"
              icon="Delete"
              @click="handleDelete(scope.row)"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.page"
        :limit.sync="queryParams.limit"
        @pagination="getList"
      />
    </el-card>
    <!-- 添加或修改线索对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="线索名称" prop="clueName">
          <el-input v-model="form.clueName" placeholder="请输入线索名称" />
        </el-form-item>
        <el-form-item label="线索来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入线索来源" />
        </el-form-item>
        <el-form-item label="线索状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择线索状态">
            <el-option label="待跟进" value="0" />
            <el-option label="跟进中" value="1" />
            <el-option label="已转化" value="2" />
            <el-option label="已失效" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="客户经理" prop="accountManagerId">
          <el-select v-model="form.accountManagerId" placeholder="请选择客户经理">
            <el-option label="张三" value="1" />
            <el-option label="李四" value="2" />
            <el-option label="王五" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否公海池" prop="isPublicPool">
          <el-switch v-model="form.isPublicPool" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { getClueList, addClue, updateClue, deleteClue, deleteClueByIds, autoRecycleOverdueClues } from '@/api/crm/clue'
import { CrmClue } from '@/types/crm/clue'

const router = useRouter()
const loading = ref<boolean>(true)
const open = ref<boolean>(false)
const title = ref<string>('')
const total = ref<number>(0)
const selectedRowKeys = ref<string[]>([])
const multipleSelection = ref<CrmClue[]>([])
const form = reactive<CrmClue>({})
const queryParams = reactive<CrmClue>({
  page: 1,
  limit: 10
})
const rules = reactive({
  clueName: [
    { required: true, message: '线索名称不能为空', trigger: 'blur' }
  ],
  source: [
    { required: true, message: '线索来源不能为空', trigger: 'blur' }
  ],
  status: [
    { required: true, message: '线索状态不能为空', trigger: 'change' }
  ]
})
const clueList = ref<CrmClue[]>([])

/** 查询线索列表 */
const getList = async () => {
  loading.value = true
  try {
    const response = await getClueList(queryParams)
    clueList.value = response.data
    total.value = response.data.length
  } catch (error) {
    console.error('查询线索列表失败:', error)
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.page = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  Object.assign(queryParams, {
    page: 1,
    limit: 10,
    clueName: undefined,
    source: undefined,
    status: undefined,
    isPublicPool: undefined
  })
  getList()
}

/** 多选框选中数据 */
const handleSelectionChange = (selection: CrmClue[]) => {
  multipleSelection.value = selection
}

/** 新增按钮操作 */
const handleAdd = () => {
  open.value = true
  title.value = '新增线索'
  Object.assign(form, {
    clueName: undefined,
    source: undefined,
    status: undefined,
    accountManagerId: undefined,
    isPublicPool: false
  })
}

/** 修改按钮操作 */
const handleUpdate = (row: CrmClue) => {
  open.value = true
  title.value = '修改线索'
  Object.assign(form, row)
}

/** 提交按钮 */
const submitForm = async () => {
  try {
    if (form.id) {
      await updateClue(form)
      ElMessage.success('修改线索成功')
    } else {
      await addClue(form)
      ElMessage.success('新增线索成功')
    }
    open.value = false
    getList()
  } catch (error) {
    console.error('保存线索失败:', error)
    ElMessage.error('保存线索失败')
  }
}

/** 取消按钮 */
const cancel = () => {
  open.value = false
}

/** 删除按钮操作 */
const handleDelete = async (row: CrmClue) => {
  try {
    await ElMessageBox.confirm('确定要删除这条线索吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteClue(row.id!)
    ElMessage.success('删除线索成功')
    getList()
  } catch (error) {
    console.error('删除线索失败:', error)
    ElMessage.error('删除线索失败')
  }
}

/** 批量删除按钮操作 */
const handleBatchDelete = async () => {
  if (multipleSelection.value.length === 0) {
    ElMessage.warning('请选择要删除的线索')
    return
  }
  try {
    await ElMessageBox.confirm('确定要删除选中的线索吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = multipleSelection.value.map(item => item.id).join(',')
    await deleteClueByIds(ids)
    ElMessage.success('批量删除线索成功')
    getList()
  } catch (error) {
    console.error('批量删除线索失败:', error)
    ElMessage.error('批量删除线索失败')
  }
}

/** 自动回收按钮操作 */
const handleAutoRecycle = async () => {
  try {
    await ElMessageBox.confirm('确定要自动回收超期未跟进的线索吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await autoRecycleOverdueClues()
    ElMessage.success('自动回收线索成功')
    getList()
  } catch (error) {
    console.error('自动回收线索失败:', error)
    ElMessage.error('自动回收线索失败')
  }
}

/** 获取线索状态对应的文本 */
const getStatusText = (status: string) => {
  switch (status) {
    case '0':
      return '待跟进'
    case '1':
      return '跟进中'
    case '2':
      return '已转化'
    case '3':
      return '已失效'
    default:
      return '未知'
  }
}

/** 获取线索状态对应的标签类型 */
const getTagType = (status: string) => {
  switch (status) {
    case '0':
      return 'info'
    case '1':
      return 'primary'
    case '2':
      return 'success'
    case '3':
      return 'danger'
    default:
      return 'info'
  }
}

/** 页面挂载时执行 */
onMounted(() => {
  getList()
})
</script>

<style scoped>
.clue-list-container {
  padding: 20px;
}

.clue-list-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
