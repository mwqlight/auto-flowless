<script setup lang="ts">
import { ref, reactive } from 'vue';
import { ElMessage, ElForm, ElDialog } from 'element-plus';
import { getLiveStreamList, addLiveStream, updateLiveStream, deleteLiveStream, startLiveStream, stopLiveStream } from '@/api/live-stream';
import { LiveStream, LiveStreamQuery } from '@/api/live-stream/types';

// 页面标题
const pageTitle = '直播流列表';

// 加载状态
const loading = ref(false);

// 表格数据
const liveStreamList = ref<LiveStream[]>([]);

// 分页信息
const pagination = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0
});

// 查询参数
const queryParams = reactive<LiveStreamQuery>({
  pageNum: 1,
  pageSize: 10,
  liveName: '',
  protocol: ''
});

// 表单引用
const queryFormRef = ref<InstanceType<typeof ElForm>>();
const liveStreamFormRef = ref<InstanceType<typeof ElForm>>();

// 对话框
const dialog = reactive({
  visible: false,
  title: '添加直播流'
});

// 表单数据
const formData = reactive<LiveStream>({
  liveName: '',
  liveUrl: '',
  protocol: 'rtmp'
});

// 表单规则
const rules = reactive({
  liveName: [{ required: true, message: '直播流名称不能为空', trigger: 'blur' }],
  liveUrl: [{ required: true, message: '直播流URL不能为空', trigger: 'blur' }],
  protocol: [{ required: true, message: '协议不能为空', trigger: 'change' }]
});

// 初始化数据
function initData() {
  loading.value = true;
  getLiveStreamList(queryParams).then(response => {
    liveStreamList.value = response.data;
    pagination.total = response.total;
    loading.value = false;
  }).catch(error => {
    ElMessage.error('查询失败：' + error.message);
    loading.value = false;
  });
}

// 查询数据
function handleQuery() {
  queryParams.pageNum = 1;
  initData();
}

// 重置查询
function handleReset() {
  if (queryFormRef.value) {
    queryFormRef.value.resetFields();
  }
  handleQuery();
}

// 打开添加对话框
function handleAdd() {
  dialog.title = '添加直播流';
  dialog.visible = true;
  if (liveStreamFormRef.value) {
    liveStreamFormRef.value.resetFields();
  }
}

// 打开编辑对话框
function handleEdit(row: LiveStream) {
  dialog.title = '编辑直播流';
  dialog.visible = true;
  Object.assign(formData, row);
}

// 提交表单
function handleSubmit() {
  if (!liveStreamFormRef.value) return;
  
  liveStreamFormRef.value.validate((valid: boolean) => {
    if (valid) {
      const api = formData.liveId ? updateLiveStream : addLiveStream;
      api(formData).then(() => {
        ElMessage.success('操作成功');
        dialog.visible = false;
        initData();
      }).catch(error => {
        ElMessage.error('操作失败：' + error.message);
      });
    }
  });
}

// 删除直播流
function handleDelete(row: LiveStream) {
  deleteLiveStream(row.liveId).then(() => {
    ElMessage.success('删除成功');
    initData();
  }).catch(error => {
    ElMessage.error('删除失败：' + error.message);
  });
}

// 开始直播
function handleStartLive(row: LiveStream) {
  startLiveStream(row.liveId).then(() => {
    ElMessage.success('开始直播成功');
    initData();
  }).catch(error => {
    ElMessage.error('开始直播失败：' + error.message);
  });
}

// 停止直播
function handleStopLive(row: LiveStream) {
  stopLiveStream(row.liveId).then(() => {
    ElMessage.success('停止直播成功');
    initData();
  }).catch(error => {
    ElMessage.error('停止直播失败：' + error.message);
  });
}

// 分页变化
function handlePageChange(pageNum: number) {
  queryParams.pageNum = pageNum;
  pagination.pageNum = pageNum;
  initData();
}

// 页大小变化
function handlePageSizeChange(pageSize: number) {
  queryParams.pageSize = pageSize;
  pagination.pageSize = pageSize;
  initData();
}

// 初始化
initData();
</script>

<template>
  <div class="app-container">
    <!-- 顶部查询区域 -->
    <div class="search-form">
      <el-form ref="queryFormRef" :model="queryParams" inline>
        <el-form-item label="直播流名称">
          <el-input
            v-model="queryParams.liveName"
            placeholder="请输入直播流名称"
            clearable
            style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="协议">
          <el-select
            v-model="queryParams.protocol"
            placeholder="请选择协议"
            clearable
            style="width: 150px"
          >
            <el-option label="RTSP" value="rtsp" />
            <el-option label="RTMP" value="rtmp" />
            <el-option label="FLV" value="flv" />
            <el-option label="HLS" value="hls" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 按钮区域 -->
    <div class="button-group">
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>
        添加直播流
      </el-button>
    </div>

    <!-- 表格区域 -->
    <el-table
      v-loading="loading"
      :data="liveStreamList"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column prop="liveId" label="ID" width="150" />
      <el-table-column prop="liveName" label="直播流名称" min-width="180" />
      <el-table-column prop="liveUrl" label="直播流URL" min-width="300" />
      <el-table-column prop="protocol" label="协议" width="100" />
      <el-table-column prop="status" label="状态" width="120">
        <template #default="scope">
          <el-tag :type="scope.row.status === 'running' ? 'success' : 'warning'">
            {{ scope.row.status === 'running' ? '直播中' : '已停止' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="viewerCount" label="观看人数" width="120" />
      <el-table-column prop="liveType" label="类型" width="120" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="250" fixed="right">
        <template #default="scope">
          <el-button
            v-if="scope.row.status === 'stopped'"
            type="success"
            size="small"
            @click="handleStartLive(scope.row)"
            >开始直播</el-button
          >
          <el-button
            v-else
            type="warning"
            size="small"
            @click="handleStopLive(scope.row)"
            >停止直播</el-button
          >
          <el-button
            type="primary"
            size="small"
            @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button
            type="danger"
            size="small"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页区域 -->
    <div class="pagination-container">
      <el-pagination
        v-model:current-page="pagination.pageNum"
        v-model:page-size="pagination.pageSize"
        :total="pagination.total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handlePageSizeChange"
        @current-change="handlePageChange"
      />
    </div>

    <!-- 表单对话框 -->
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="500px"
      @close="dialog.visible = false"
    >
      <el-form
        ref="liveStreamFormRef"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="直播流名称" prop="liveName">
          <el-input v-model="formData.liveName" placeholder="请输入直播流名称" />
        </el-form-item>
        <el-form-item label="直播流URL" prop="liveUrl">
          <el-input v-model="formData.liveUrl" placeholder="请输入直播流URL" />
        </el-form-item>
        <el-form-item label="协议" prop="protocol">
          <el-select v-model="formData.protocol" placeholder="请选择协议">
            <el-option label="RTSP" value="rtsp" />
            <el-option label="RTMP" value="rtmp" />
            <el-option label="FLV" value="flv" />
            <el-option label="HLS" value="hls" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialog.visible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">确认</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.search-form {
  margin-bottom: 20px;
}

.button-group {
  margin-bottom: 20px;
}

.pagination-container {
  text-align: right;
  margin-top: 20px;
}
</style>
