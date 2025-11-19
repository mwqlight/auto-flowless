<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { getLiveStreamById, getLiveStreamUrl } from '@/api/live-stream';
import { LiveStream } from '@/api/live-stream/types';

// 路由
const route = useRoute();
const router = useRouter();

// 直播流ID
const liveId = route.params.liveId as string;

// 直播流信息
const liveStream = ref<LiveStream | null>(null);

// 加载状态
const loading = ref(false);

// 直播观看地址
const liveUrl = ref<string | null>(null);

// 初始化数据
function initData() {
  loading.value = true;
  getLiveStreamById(liveId).then(response => {
    liveStream.value = response.data;
    loading.value = false;
    
    // 如果直播正在运行，获取观看地址
    if (response.data.status === 'running') {
      getLiveStreamUrl(liveId).then(urlResponse => {
        liveUrl.value = urlResponse.data;
      }).catch(error => {
        ElMessage.error('获取观看地址失败：' + error.message);
      });
    }
  }).catch(error => {
    ElMessage.error('查询失败：' + error.message);
    loading.value = false;
    router.push('/video-monitor/live-stream');
  });
}

// 复制观看地址
function copyLiveUrl() {
  if (liveUrl.value) {
    navigator.clipboard.writeText(liveUrl.value).then(() => {
      ElMessage.success('复制成功');
    }).catch(error => {
      ElMessage.error('复制失败：' + error.message);
    });
  }
}

// 初始化
onMounted(() => {
  initData();
});
</script>

<template>
  <div class="app-container">
    <!-- 顶部标题 -->
    <div class="page-header">
      <el-button @click="$router.go(-1)" type="primary" size="small">
        <el-icon><ArrowLeft /></el-icon>
        返回
      </el-button>
      <h2 style="margin-left: 20px;">{{ liveStream?.liveName }} - 直播流详情</h2>
    </div>

    <!-- 直播流信息 -->
    <el-card v-loading="loading" class="info-card">
      <template #header>
        <div class="card-header">直播流信息</div>
      </template>
      <el-descriptions :column="1" border>
        <el-descriptions-item label="直播流ID">{{ liveStream?.liveId }}</el-descriptions-item>
        <el-descriptions-item label="直播流名称">{{ liveStream?.liveName }}</el-descriptions-item>
        <el-descriptions-item label="直播流URL">{{ liveStream?.liveUrl }}</el-descriptions-item>
        <el-descriptions-item label="协议">{{ liveStream?.protocol }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="liveStream?.status === 'running' ? 'success' : 'warning'">
            {{ liveStream?.status === 'running' ? '直播中' : '已停止' }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="观看人数">{{ liveStream?.viewerCount || 0 }}人</el-descriptions-item>
        <el-descriptions-item label="直播类型">{{ liveStream?.liveType }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ liveStream?.createTime }}</el-descriptions-item>
        <el-descriptions-item v-if="liveStream?.startTime" label="开始时间">{{ liveStream?.startTime }}</el-descriptions-item>
        <el-descriptions-item v-if="liveStream?.endTime" label="结束时间">{{ liveStream?.endTime }}</el-descriptions-item>
      </el-descriptions>
    </el-card>

    <!-- 直播播放器 -->
    <el-card class="player-card">
      <template #header>
        <div class="card-header">
          直播播放器
          <el-button
            v-if="liveStream?.status === 'running' && liveUrl"
            type="primary"
            size="small"
            @click="copyLiveUrl"
            style="margin-left: 20px;"
          >
            <el-icon><DocumentCopy /></el-icon>
            复制观看地址
          </el-button>
        </div>
      </template>
      <div class="player-container">
        <div v-if="liveStream?.status === 'running' && liveUrl" class="live-player">
          <!-- TODO: 集成直播播放器组件 -->
          <div class="placeholder-player">
            <el-icon class="video-icon"><VideoCamera /></el-icon>
            <p>直播播放器占位</p>
            <p>将集成VLC或其他直播播放组件</p>
            <p v-if="liveUrl">观看地址：{{ liveUrl }}</p>
          </div>
        </div>
        <div v-else class="live-not-running">
          <el-icon class="warning-icon"><Warning /></el-icon>
          <p>直播未开始或已停止</p>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.page-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.info-card {
  margin-bottom: 20px;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.player-card {
  margin-bottom: 20px;
}

.player-container {
  height: 400px;
  background-color: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #e8e8e8;
}

.placeholder-player {
  text-align: center;
  color: #999;
}

.video-icon {
  font-size: 80px;
  margin-bottom: 20px;
}

.live-not-running {
  text-align: center;
  color: #f56c6c;
}

.warning-icon {
  font-size: 60px;
  margin-bottom: 20px;
}
</style>
