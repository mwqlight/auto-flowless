<template>
  <div class="opportunity-list-container">
    <el-page-header @back="$router.back" content="商机管理" />
    <el-card class="opportunity-list-card">
      <template #header>
        <div class="card-header">
          <span>商机列表</span>
          <el-button type="primary" @click="handleAdd">新增商机</el-button>
          <el-button @click="handleAutoRecycle">自动回收</el-button>
        </div>
      </template>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
        <el-form-item label="商机名称" prop="opportunityName">
          <el-input
            v-model="queryParams.opportunityName"
            placeholder="请输入商机名称"
            clearable
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="商机阶段" prop="stage">
          <el-select
            v-model="queryParams.stage"
            placeholder="请选择商机阶段"
            clearable
            style="width: 140px"
          >
            <el-option label="线索" value="0" />
            <el-option label="潜在客户" value="1" />
            <el-option label="意向客户" value="2" />
            <el-option label="成交客户" value="3" />
            <el-option label="流失客户" value="4" />
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
      <el-table v-loading="loading" :data="opportunityList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="商机名称" align="center" prop="opportunityName" show-overflow-tooltip />
        <el-table-column label="关联线索" align="center" prop="clueId" show-overflow-tooltip />
        <el-table-column label="关联客户" align="center" prop="customerId" show-overflow-tooltip />
        <el-table-column label="商机阶段" align="center" prop="stage" show-overflow-tooltip>
          <template #default="scope">
            <el-tag :type="getTagType(scope.row.stage)" size="small">
              {{ getStageText(scope.row.stage) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="预计金额" align="center" prop="estimatedAmount" show-overflow-tooltip>
          <template #default="scope">
            {{ scope.row.estimatedAmount | currency('¥') }}
          </template>
        </el-table-column>
        <el-table-column label="预计成交时间" align="center" prop="estimatedCloseTime" show-overflow-tooltip />
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
    <!-- 添加或修改商机对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商机名称" prop="opportunityName">
          <el-input v-model="form.opportunityName" placeholder="请输入商机名称" />
        </el-form-item>
        <el-form-item label="关联线索" prop="clueId">
          <el-input v-model="form.clueId" placeholder="请输入关联线索ID" />
        </el-form-item>
        <el-form-item label="关联客户" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入关联客户ID" />
        </el-form-item>
        <el-form-item label="商机阶段" prop="stage">
          <el-select v-model="form.stage" placeholder="请选择商机阶段">
            <el-option label="线索" value="0" />
            <el-option label="潜在客户" value="1" />
            <el-option label="意向客户" value="2" />
            <el-option label="成交客户" value="3" />
            <el-option label="流失客户" value="4" />
          </el-select>
        </el-form-item>
        <el-form-item label="预计金额" prop="estimatedAmount">
          <el-input-number v-model="form.estimatedAmount" placeholder="请输入预计金额" :precision="2" :step="0.01" />
        </el-form-item>
        <el-form-item label="预计成交时间" prop="estimatedCloseTime">
          <el-date-picker v-model="form.estimatedCloseTime" placeholder="请选择预计成交时间" type="date" />
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
import { getOpportunityList, addOpportunity, updateOpportunity, deleteOpportunity, deleteOpportunityByIds, autoRecycleOverdueOpportunities } from '@/api/crm/opportunity'
import { CrmOpportunity } from '@/types/crm/opportunity'

const router = useRouter()
const loading = ref<boolean>(true)
const open = ref<boolean>(false)
const title = ref<string>('')
const total = ref<number>(0)
const selectedRowKeys = ref<string[]>([])
const multipleSelection = ref<CrmOpportunity[]>([])
const form = reactive<CrmOpportunity>({})
const queryParams = reactive<CrmOpportunity>({
  page: 1,
  limit: 10
})
const rules = reactive({
  opportunityName: [
    { required: true, message: '商机名称不能为空', trigger: 'blur' }
  ],
  stage: [
    { required: true, message: '商机阶段不能为空', trigger: 'change' }
  ],
  estimatedAmount: [
    { required: true, message: '预计金额不能为空', trigger: 'blur' }
  ]
})
const opportunityList = ref<CrmOpportunity[]>([])

/** 查询商机列表 */
const getList = async () => {
  loading.value = true
  try {
    const response = await getOpportunityList(queryParams)
    opportunityList.value = response.data
    total.value = response.data.length
  } catch (error) {
    console.error('查询商机列表失败:', error)
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
    opportunityName: undefined,
    stage: undefined,
    isPublicPool: undefined
  })
  getList()
}

/** 多选框选中数据 */
const handleSelectionChange = (selection: CrmOpportunity[]) => {
  multipleSelection.value = selection
}

/** 新增按钮操作 */
const handleAdd = () => {
  open.value = true
  title.value = '新增商机'
  Object.assign(form, {
    opportunityName: undefined,
    clueId: undefined,
    customerId: undefined,
    stage: undefined,
    estimatedAmount: undefined,
    estimatedCloseTime: undefined,
    accountManagerId: undefined,
    isPublicPool: false
  })
}

/** 修改按钮操作 */
const handleUpdate = (row: CrmOpportunity) => {
  open.value = true
  title.value = '修改商机'
  Object.assign(form, row)
}

/** 提交按钮 */
const submitForm = async () => {
  try {
    if (form.id) {
      await updateOpportunity(form)
      ElMessage.success('修改商机成功')
    } else {
      await addOpportunity(form)
      ElMessage.success('新增商机成功')
    }
    open.value = false
    getList()
  } catch (error) {
    console.error('保存商机失败:', error)
    ElMessage.error('保存商机失败')
  }
}

/** 取消按钮 */
const cancel = () => {
  open.value = false
}

/** 删除按钮操作 */
const handleDelete = async (row: CrmOpportunity) => {
  try {
    await ElMessageBox.confirm('确定要删除这条商机吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteOpportunity(row.id!)
    ElMessage.success('删除商机成功')
    getList()
  } catch (error) {
    console.error('删除商机失败:', error)
    ElMessage.error('删除商机失败')
  }
}

/** 批量删除按钮操作 */
const handleBatchDelete = async () => {
  if (multipleSelection.value.length === 0) {
    ElMessage.warning('请选择要删除的商机')
    return
  }
  try {
    await ElMessageBox.confirm('确定要删除选中的商机吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = multipleSelection.value.map(item => item.id).join(',')
    await deleteOpportunityByIds(ids)
    ElMessage.success('批量删除商机成功')
    getList()
  } catch (error) {
    console.error('批量删除商机失败:', error)
    ElMessage.error('批量删除商机失败')
  }
}

/** 自动回收按钮操作 */
const handleAutoRecycle = async () => {
  try {
    await ElMessageBox.confirm('确定要自动回收超期未跟进的商机吗?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await autoRecycleOverdueOpportunities()
    ElMessage.success('自动回收商机成功')
    getList()
  } catch (error) {
    console.error('自动回收商机失败:', error)
    ElMessage.error('自动回收商机失败')
  }
}

/** 获取商机阶段对应的文本 */
const getStageText = (stage: string) => {
  switch (stage) {
    case '0':
      return '线索'
    case '1':
      return '潜在客户'
    case '2':
      return '意向客户'
    case '3':
      return '成交客户'
    case '4':
      return '流失客户'
    default:
      return '未知'
  }
}

/** 获取商机阶段对应的标签类型 */
const getTagType = (stage: string) => {
  switch (stage) {
    case '0':
      return 'info'
    case '1':
      return 'primary'
    case '2':
      return 'success'
    case '3':
      return 'warning'
    case '4':
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
.opportunity-list-container {
  padding: 20px;
}

.opportunity-list-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
