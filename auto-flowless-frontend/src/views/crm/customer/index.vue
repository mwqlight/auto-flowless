<template>
  <div class="app-container">
    <div class="search-wrapper">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="客户名称" prop="customerName">
          <el-input
            v-model="queryParams.customerName"
            placeholder="请输入客户名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="客户经理" prop="managerName">
          <el-input
            v-model="queryParams.managerName"
            placeholder="请输入客户经理"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="客户等级" prop="customerLevel">
          <el-select
            v-model="queryParams.customerLevel"
            placeholder="请选择客户等级"
            clearable
            style="width: 160px"
          >
            <el-option
              v-for="item in customerLevelOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否公海" prop="isPublicPool">
          <el-select
            v-model="queryParams.isPublicPool"
            placeholder="请选择是否公海"
            clearable
            style="width: 160px"
          >
            <el-option label="是" value="1" />
            <el-option label="否" value="0" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <div class="handle-wrapper">
        <el-button v-hasPermi="['crm:customer:add']" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
        <el-button v-hasPermi="['crm:customer:edit']" type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
        <el-button v-hasPermi="['crm:customer:remove']" type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
        <el-button v-hasPermi="['crm:customer:recycle']" type="warning" icon="el-icon-refresh-left" size="mini" @click="handleAutoRecycle">自动回收</el-button>
        <el-button icon="el-icon-more" size="mini" @click="showSearch = !showSearch">
          {{ showSearch ? '收起' : '展开' }}搜索
        </el-button>
      </div>
    </div>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户名称" prop="customerName" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="统一社会信用代码" prop="creditCode" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="行业类型" prop="industryType" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="企业规模" prop="enterpriseScale" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="客户等级" prop="customerLevel" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="客户经理" prop="managerName" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="最后跟进时间" prop="lastFollowTime" align="center" :show-overflow-tooltip="true" width="180" />
      <el-table-column label="跟进状态" prop="followStatus" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="是否公海" prop="isPublicPool" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isPublicPool ? 'warning' : 'success'">
            {{ scope.row.isPublicPool ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center" :show-overflow-tooltip="true" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['crm:customer:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['crm:customer:remove']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改客户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户名称" prop="customerName">
              <el-input v-model="form.customerName" placeholder="请输入客户名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="统一社会信用代码" prop="creditCode">
              <el-input v-model="form.creditCode" placeholder="请输入统一社会信用代码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="行业类型" prop="industryType">
              <el-select v-model="form.industryType" placeholder="请选择行业类型" style="width: 100%">
                <el-option
                  v-for="item in industryTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业规模" prop="enterpriseScale">
              <el-select v-model="form.enterpriseScale" placeholder="请选择企业规模" style="width: 100%">
                <el-option
                  v-for="item in enterpriseScaleOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="控股股东" prop="controllingShareholder">
              <el-input v-model="form.controllingShareholder" placeholder="请输入控股股东" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户等级" prop="customerLevel">
              <el-select v-model="form.customerLevel" placeholder="请选择客户等级" style="width: 100%">
                <el-option
                  v-for="item in customerLevelOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="客户标签" prop="tags">
          <tag-input v-model="form.tags" placeholder="请输入客户标签，按Enter键添加" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户经理ID" prop="managerId">
              <el-input v-model="form.managerId" placeholder="请输入客户经理ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户经理名称" prop="managerName">
              <el-input v-model="form.managerName" placeholder="请输入客户经理名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="最后跟进时间" prop="lastFollowTime">
          <el-date-picker
            v-model="form.lastFollowTime"
            type="datetime"
            placeholder="请选择最后跟进时间"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="跟进状态" prop="followStatus">
          <el-input v-model="form.followStatus" placeholder="请输入跟进状态" />
        </el-form-item>
        <el-form-item label="是否公海" prop="isPublicPool">
          <el-radio-group v-model="form.isPublicPool">
            <el-radio label="0">否</el-radio>
            <el-radio label="1">是</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, onMounted } from 'vue'
import { getCustomerList, getCustomer, delCustomer, addCustomer, updateCustomer, autoRecycleOverdueCustomers, getCustomerLevelOptions, getIndustryTypeOptions, getEnterpriseScaleOptions } from '@/api/crm/customer'
import { ElMessage } from 'element-plus'
import TagInput from '@/components/TagInput/index.vue'

export default defineComponent({
  name: 'Customer',
  components: {
    TagInput
  },
  setup() {
    const loading = ref<boolean>(true)
    const showSearch = ref<boolean>(true)
    const title = ref<string>('')
    const open = ref<boolean>(false)
    const form = reactive({
      id: undefined,
      customerName: '',
      creditCode: '',
      industryType: '',
      enterpriseScale: '',
      controllingShareholder: '',
      customerLevel: '',
      tags: [],
      managerId: undefined,
      managerName: '',
      lastFollowTime: undefined,
      followStatus: '',
      isPublicPool: 0
    })
    const rules = reactive({
      customerName: [
        { required: true, message: '客户名称不能为空', trigger: 'blur' }
      ]
    })
    const customerList = ref<any[]>([])
    const total = ref<number>(0)
    const multipleSelection = ref<any[]>([])
    const queryParams = reactive({
      pageNum: 1,
      pageSize: 10,
      customerName: undefined,
      managerName: undefined,
      customerLevel: undefined,
      isPublicPool: undefined
    })
    const customerLevelOptions = ref<any[]>([])
    const industryTypeOptions = ref<any[]>([])
    const enterpriseScaleOptions = ref<any[]>([])

    // 分页查询
    const getList = async () => {
      loading.value = true
      try {
        const res = await getCustomerList(queryParams)
        customerList.value = res.rows
        total.value = res.total
      } catch (error) {
        console.error('查询客户列表失败:', error)
      } finally {
        loading.value = false
      }
    }

    // 搜索按钮点击事件
    const handleQuery = () => {
      queryParams.pageNum = 1
      getList()
    }

    // 重置按钮点击事件
    const resetQuery = () => {
      Object.assign(queryParams, {
        customerName: undefined,
        managerName: undefined,
        customerLevel: undefined,
        isPublicPool: undefined
      })
      handleQuery()
    }

    // 新增按钮点击事件
    const handleAdd = () => {
      Object.assign(form, {
        id: undefined,
        customerName: '',
        creditCode: '',
        industryType: '',
        enterpriseScale: '',
        controllingShareholder: '',
        customerLevel: '',
        tags: [],
        managerId: undefined,
        managerName: '',
        lastFollowTime: undefined,
        followStatus: '',
        isPublicPool: 0
      })
      title.value = '新增客户'
      open.value = true
    }

    // 修改按钮点击事件
    const handleUpdate = async (row: any) => {
      try {
        const res = await getCustomer(row.id)
        Object.assign(form, res)
        title.value = '修改客户'
        open.value = true
      } catch (error) {
        console.error('查询客户详情失败:', error)
      }
    }

    // 删除按钮点击事件
    const handleDelete = async (row?: any) => {
      const ids = row ? [row.id] : multipleSelection.value.map(item => item.id)
      const name = row ? row.customerName : multipleSelection.value.map(item => item.customerName).join(',')
      ElMessage.confirm(`确定要删除客户「${name}」吗？`, '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          await delCustomer(ids)
          ElMessage.success('删除成功')
          getList()
          multipleSelection.value = []
        } catch (error) {
          console.error('删除客户失败:', error)
        }
      }).catch(() => {})
    }

    // 自动回收按钮点击事件
    const handleAutoRecycle = async () => {
      ElMessage.confirm('确定要自动回收超期未跟进的客户吗？', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          await autoRecycleOverdueCustomers()
          ElMessage.success('自动回收成功')
          getList()
        } catch (error) {
          console.error('自动回收失败:', error)
        }
      }).catch(() => {})
    }

    // 表单提交
    const submitForm = async () => {
      const formRef = document.querySelector('.el-form') as any
      formRef.validate(async (valid: boolean) => {
        if (valid) {
          try {
            if (form.id) {
              await updateCustomer(form)
              ElMessage.success('修改成功')
            } else {
              await addCustomer(form)
              ElMessage.success('新增成功')
            }
            open.value = false
            getList()
          } catch (error) {
            console.error('保存客户失败:', error)
          }
        }
      })
    }

    // 取消按钮点击事件
    const cancel = () => {
      open.value = false
    }

    // 表格选择项变化
    const handleSelectionChange = (selection: any[]) => {
      multipleSelection.value = selection
    }

    // 获取选项数据
    const getOptions = async () => {
      try {
        customerLevelOptions.value = await getCustomerLevelOptions()
        industryTypeOptions.value = await getIndustryTypeOptions()
        enterpriseScaleOptions.value = await getEnterpriseScaleOptions()
      } catch (error) {
        console.error('获取选项数据失败:', error)
      }
    }

    onMounted(() => {
      getList()
      getOptions()
    })

    return {
      loading,
      showSearch,
      title,
      open,
      form,
      rules,
      customerList,
      total,
      multipleSelection,
      queryParams,
      customerLevelOptions,
      industryTypeOptions,
      enterpriseScaleOptions,
      handleQuery,
      resetQuery,
      handleAdd,
      handleUpdate,
      handleDelete,
      handleAutoRecycle,
      submitForm,
      cancel,
      handleSelectionChange
    }
  }
})
</script>

<style scoped>
.search-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.handle-wrapper {
  display: flex;
  gap: 10px;
}
</style>