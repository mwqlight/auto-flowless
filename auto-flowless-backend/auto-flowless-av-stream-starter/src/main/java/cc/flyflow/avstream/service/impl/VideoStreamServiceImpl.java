package cc.flyflow.avstream.service.impl;

import cc.flyflow.avstream.entity.VideoStream;
import cc.flyflow.avstream.service.VideoStreamService;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VideoStreamServiceImpl implements VideoStreamService {
    // 模拟数据库存储
    private static final Map<String, VideoStream> videoStreamMap = new HashMap<>();
    // 存储视频捕获器
    private static final Map<String, FFmpegFrameGrabber> grabberMap = new HashMap<>();
    // 存储播放线程
    private static final Map<String, Thread> playThreadMap = new HashMap<>();
    // 存储播放状态
    private static final Map<String, Boolean> isPlayingMap = new HashMap<>();
    // 线程池
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @Override
    public List<VideoStream> getAllVideoStreams() {
        return new ArrayList<>(videoStreamMap.values());
    }
    
    @Override
    public VideoStream getVideoStreamById(String streamId) {
        return videoStreamMap.get(streamId);
    }
    
    @Override
    public boolean addVideoStream(VideoStream videoStream) {
        try {
            String streamId = UUID.randomUUID().toString();
            videoStream.setStreamId(streamId);
            videoStream.setCreateTime(LocalDateTime.now());
            videoStream.setUpdateTime(LocalDateTime.now());
            videoStream.setStatus("stopped");
            videoStream.setPlayProgress(0L);
            
            // 自动检测视频流类型
            if (videoStream.getStreamUrl().contains("dahua")) {
                videoStream.setStreamType("dahua");
            } else if (videoStream.getStreamUrl().contains("hikvision")) {
                videoStream.setStreamType("hikvision");
            } else {
                videoStream.setStreamType("other");
            }
            
            videoStreamMap.put(streamId, videoStream);
            log.info("添加视频流成功: {}", videoStream);
            return true;
        } catch (Exception e) {
            log.error("添加视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean updateVideoStream(VideoStream videoStream) {
        try {
            if (videoStreamMap.containsKey(videoStream.getStreamId())) {
                VideoStream existingStream = videoStreamMap.get(videoStream.getStreamId());
                existingStream.setStreamName(videoStream.getStreamName());
                existingStream.setStreamUrl(videoStream.getStreamUrl());
                existingStream.setProtocol(videoStream.getProtocol());
                existingStream.setUpdateTime(LocalDateTime.now());
                
                videoStreamMap.put(videoStream.getStreamId(), existingStream);
                log.info("更新视频流成功: {}", existingStream);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean deleteVideoStream(String streamId) {
        try {
            if (videoStreamMap.containsKey(streamId)) {
                // 先停止视频流
                stopVideoStream(streamId);
                videoStreamMap.remove(streamId);
                log.info("删除视频流成功: {}", streamId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("删除视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean playVideoStream(String streamId) {
        try {
            if (videoStreamMap.containsKey(streamId)) {
                // 先停止已经在播放的视频流
                if (isPlayingMap.getOrDefault(streamId, false)) {
                    stopVideoStream(streamId);
                }
                
                VideoStream videoStream = videoStreamMap.get(streamId);
                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoStream.getStreamUrl());
                grabber.setOption("rtsp_transport", "tcp"); // 使用TCP传输
                grabber.start();
                
                grabberMap.put(streamId, grabber);
                isPlayingMap.put(streamId, true);
                
                // 创建播放线程
                Thread playThread = new Thread(() -> {
                    try {
                        Frame frame;
                        while ((frame = grabber.grabImage()) != null && isPlayingMap.getOrDefault(streamId, false)) {
                            // 这里可以添加视频帧处理逻辑，比如推流到前端
                            // TODO: 实现视频帧的处理和传输
                            TimeUnit.MILLISECONDS.sleep(40); // 控制帧率
                        }
                    } catch (FrameGrabber.Exception | InterruptedException e) {
                        if (isPlayingMap.getOrDefault(streamId, false)) {
                            log.error("视频流播放异常: {}", e.getMessage(), e);
                        }
                    } finally {
                        try {
                            grabber.stop();
                            grabber.release();
                        } catch (FrameGrabber.Exception e) {
                            log.error("释放视频捕获器失败: {}", e.getMessage(), e);
                        }
                        grabberMap.remove(streamId);
                        playThreadMap.remove(streamId);
                        isPlayingMap.put(streamId, false);
                    }
                });
                
                playThread.start();
                playThreadMap.put(streamId, playThread);
                
                videoStream.setStatus("playing");
                videoStream.setPlayTime(LocalDateTime.now());
                videoStreamMap.put(streamId, videoStream);
                
                log.info("播放视频流成功: {}", streamId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("播放视频流失败: {}", e.getMessage(), e);
            isPlayingMap.put(streamId, false);
            return false;
        }
    }
    
    @Override
    public boolean pauseVideoStream(String streamId) {
        try {
            if (videoStreamMap.containsKey(streamId)) {
                // JavaCV的FFmpegFrameGrabber没有直接的暂停方法，这里通过停止当前播放并保存进度来模拟暂停
                // 实际应用中可能需要更复杂的实现
                stopVideoStream(streamId);
                
                VideoStream videoStream = videoStreamMap.get(streamId);
                videoStream.setStatus("paused");
                videoStreamMap.put(streamId, videoStream);
                
                log.info("暂停视频流成功: {}", streamId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("暂停视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean fastForwardVideoStream(String streamId, long seconds) {
        try {
            if (videoStreamMap.containsKey(streamId) && isPlayingMap.getOrDefault(streamId, false)) {
                FFmpegFrameGrabber grabber = grabberMap.get(streamId);
                if (grabber != null) {
                    long currentTimestamp = grabber.getTimestamp();
                    long newTimestamp = currentTimestamp + seconds * 1000000L; // JavaCV使用微秒
                    grabber.setTimestamp(newTimestamp);
                    
                    VideoStream videoStream = videoStreamMap.get(streamId);
                    videoStream.setPlayProgress(videoStream.getPlayProgress() + seconds);
                    videoStreamMap.put(streamId, videoStream);
                    
                    log.info("快进视频流成功: {}, 快进秒数: {}", streamId, seconds);
                    return true;
                }
            }
            return false;
        } catch (FrameGrabber.Exception e) {
            log.error("快进视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean fastBackwardVideoStream(String streamId, long seconds) {
        try {
            if (videoStreamMap.containsKey(streamId) && isPlayingMap.getOrDefault(streamId, false)) {
                FFmpegFrameGrabber grabber = grabberMap.get(streamId);
                if (grabber != null) {
                    long currentTimestamp = grabber.getTimestamp();
                    long newTimestamp = Math.max(currentTimestamp - seconds * 1000000L, 0); // JavaCV使用微秒
                    grabber.setTimestamp(newTimestamp);
                    
                    VideoStream videoStream = videoStreamMap.get(streamId);
                    long newProgress = Math.max(videoStream.getPlayProgress() - seconds, 0);
                    videoStream.setPlayProgress(newProgress);
                    videoStreamMap.put(streamId, videoStream);
                    
                    log.info("快退视频流成功: {}, 快退秒数: {}", streamId, seconds);
                    return true;
                }
            }
            return false;
        } catch (FrameGrabber.Exception e) {
            log.error("快退视频流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean stopVideoStream(String streamId) {
        try {
            if (videoStreamMap.containsKey(streamId)) {
                // 设置播放状态为false
                isPlayingMap.put(streamId, false);
                
                // 等待播放线程结束
                Thread playThread = playThreadMap.get(streamId);
                if (playThread != null && playThread.isAlive()) {
                    playThread.interrupt();
                    try {
                        playThread.join(1000); // 等待1秒
                    } catch (InterruptedException e) {
                        log.error("等待播放线程结束失败: {}", e.getMessage(), e);
                    }
                }
                
                // 释放资源
                FFmpegFrameGrabber grabber = grabberMap.get(streamId);
                if (grabber != null) {
                    try {
                        grabber.stop();
                        grabber.release();
                    } catch (FrameGrabber.Exception e) {
                        log.error("释放视频捕获器失败: {}", e.getMessage(), e);
                    }
                    grabberMap.remove(streamId);
                }
                
                playThreadMap.remove(streamId);
                
                VideoStream videoStream = videoStreamMap.get(streamId);
                videoStream.setStatus("stopped");
                videoStream.setPlayProgress(0L);
                videoStreamMap.put(streamId, videoStream);
                
                log.info("停止视频流成功: {}", streamId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("停止视频流失败: {}", e.getMessage(), e);
            isPlayingMap.put(streamId, false);
            return false;
        }
    }
}
