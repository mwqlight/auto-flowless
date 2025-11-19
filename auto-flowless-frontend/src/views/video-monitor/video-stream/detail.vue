<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { getVideoStreamById, playVideoStream, pauseVideoStream, fastForwardVideoStream, fastBackwardVideoStream, stopVideoStream } from '@/api/video-stream';
import { VideoStream } from '@/api/video-stream/types';

// 路由
const route = useRoute();
const router = useRouter();

// 视频流ID
const streamId = route.params.streamId as string;

// 视频流信息
const videoStream = ref<VideoStream | null>(null);

// 加载状态
const loading = ref(false);

// 播放控制
const playControl = reactive({
  isPlaying: false,
  currentTime: 0,
  duration: 0,
  volume: 50
});

// 初始化数据
function initData() {
  loading.value = true;
  getVideoStreamById(streamId).then(response => {
    videoStream.value = response.data;
    playControl.isPlaying = response.data.status === 'playing';
    playControl.currentTime = response.data.playProgress || 0;
    loading.value = false;
  }).catch(error => {
    ElMessage.error('查询失败：' + error.message);
    loading.value = false;
    router.push('/video-monitor/video-stream');
  });
}

// 播放视频
function handlePlay() {
  playVideoStream(streamId).then(() => {
    ElMessage.success('播放成功');
    playControl.isPlaying = true;
    if (videoStream.value) {
      videoStream.value.status = 'playing';
    }
  }).catch(error => {
    ElMessage.error('播放失败：' + error.message);
  });
}

// 暂停视频
function handlePause() {
  pauseVideoStream(streamId).then(() => {
    ElMessage.success('暂停成功');
    playControl.isPlaying = false;
    if (videoStream.value) {
      videoStream.value.status = 'paused';
    }
  }).catch(error => {
    ElMessage.error('暂停失败：' + error.message);
  });
}

// 停止视频
function handleStop() {
  stopVideoStream(streamId).then(() => {
    ElMessage.success('停止成功');
    playControl.isPlaying = false;
    playControl.currentTime = 0;
    if (videoStream.value) {
      videoStream.value.status = 'stopped';
      videoStream.value.playProgress = 0;
    }
  }).catch(error => {
    ElMessage.error('停止失败：' + error.message);
  });
}

// 快进
function handleFastForward() {
  const seconds = 10;
  fastForwardVideoStream(streamId, seconds).then(() => {
    ElMessage.success('快进成功');
    playControl.currentTime += seconds;
    if (videoStream.value) {
      videoStream.value.playProgress += seconds;
    }
  }).catch(error => {
    ElMessage.error('快进失败：' + error.message);
  });
}

// 快退
function handleFastBackward() {
  const seconds = 10;
  fastBackwardVideoStream(streamId, seconds).then(() => {
    ElMessage.success('快退成功');
    playControl.currentTime = Math.max(0, playControl.currentTime - seconds);
    if (videoStream.value) {
      videoStream.value.playProgress = Math.max(0, (videoStream.value.playProgress || 0) - seconds);
    }
  }).catch(error => {
    ElMessage.error('快退失败：' + error.message);
  });
}

// 音量变化
function handleVolumeChange(volume: number) {
  playControl.volume = volume;
  // TODO: 实现音量控制
}

// 时间变化
function handleTimeChange(time: number) {
  playControl.currentTime = time;
  // TODO: 实现时间跳转
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
      <h2 style="margin-left: 20px;">{{ videoStream?.streamName }} - 视频流详情</h2>
    </div>

    <!-- 视频流信息 -->
    <el-card v-loading="loading" class="info-card">
      <template #header>
        <div class="card-header">视频流信息</div>
      </template>
      <el-descriptions :column="1" border>
        <el-descriptions-item label="视频流ID">{{ videoStream?.streamId }}</el-descriptions-item>
        <el-descriptions-item label="视频流名称">{{ videoStream?.streamName }}</el-descriptions-item>
        <el-descriptions-item label="视频流URL">{{ videoStream?.streamUrl }}</el-descriptions-item>
        <el-descriptions-item label="协议">{{ videoStream?.protocol }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="videoStream?.status === 'playing' ? 'success' : 'warning'">
            {{ videoStream?.status === 'playing' ? '播放中' : videoStream?.status === 'paused' ? '已暂停' : '已停止' }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="播放进度">{{ (videoStream?.playProgress || 0) }}秒</el-descriptions-item>
        <el-descriptions-item label="类型">{{ videoStream?.streamType }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ videoStream?.createTime }}</el-descriptions-item>
      </el-descriptions>
    </el-card>

    <!-- 视频播放器 -->
    <el-card class="player-card">
      <template #header>
        <div class="card-header">视频播放器</div>
      </template>
      <div class="player-container">
        <!-- TODO: 集成视频播放器组件 -->
        <div class="placeholder-player">
          <el-icon class="video-icon"><VideoCamera /></el-icon>
          <p>视频播放器占位</p>
          <p>将集成VLC或其他视频播放组件</p>
        </div>
      </div>
    </el-card>

    <!-- 播放控制 -->
    <el-card class="control-card">
      <template #header>
        <div class="card-header">播放控制</div>
      </template>
      <div class="control-buttons">
        <el-button
          type="primary"
          @click="playControl.isPlaying ? handlePause() : handlePlay()"
          size="large"
        >
          <el-icon>{{ playControl.isPlaying ? <Pause /> : <PlayArrow /> }}</el-icon>
          {{ playControl.isPlaying ? '暂停' : '播放' }}
        </el-button>
        <el-button type="warning" @click="handleStop" size="large">
          <el-icon><Stop /></el-icon>
          停止
        </el-button>
        <el-button @click="handleFastBackward" size="large">
          <el-icon><SkipPrevious /></el-icon>
          快退10秒
        </el-button>
        <el-button @click="handleFastForward" size="large">
          <el-icon><SkipNext /></el-icon>
          快进10秒
        </el-button>
      </div>

      <!-- 进度条 -->
      <div class="progress-container">
        <el-slider
          v-model="playControl.currentTime"
          :max="playControl.duration || 1000"
          @change="handleTimeChange"
          style="width: 80%"
        />
        <span class="time-display">
          {{ formatTime(playControl.currentTime) }} / {{ formatTime(playControl.duration || 0) }}
        </span>
      </div>

      <!-- 音量控制 -->
      <div class="volume-container">
        <el-icon class="volume-icon"><Sound /></el-icon>
        <el-slider
          v-model="playControl.volume"
          min="0"
          max="100"
          @change="handleVolumeChange"
          style="width: 150px; margin-left: 10px;"
        />
        <span class="volume-value">{{ playControl.volume }}%</span>
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

.control-card {
  margin-bottom: 20px;
}

.control-buttons {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.progress-container {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 20px;
}

.time-display {
  width: 120px;
  text-align: center;
}

.volume-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.volume-icon {
  font-size: 20px;
}

.volume-value {
  width: 50px;
  text-align: center;
}
</style>
