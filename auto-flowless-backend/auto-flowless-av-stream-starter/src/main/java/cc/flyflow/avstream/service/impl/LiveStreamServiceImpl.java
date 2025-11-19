package cc.flyflow.avstream.service.impl;

import cc.flyflow.avstream.entity.LiveStream;
import cc.flyflow.avstream.service.LiveStreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 直播流服务实现类
 */
@Service
@Slf4j
public class LiveStreamServiceImpl implements LiveStreamService {
    
    // 模拟数据库存储
    private static final Map<String, LiveStream> liveStreamMap = new HashMap<>();
    
    @Override
    public List<LiveStream> getAllLiveStreams() {
        return new ArrayList<>(liveStreamMap.values());
    }
    
    @Override
    public LiveStream getLiveStreamById(String liveId) {
        return liveStreamMap.get(liveId);
    }
    
    @Override
    public boolean addLiveStream(LiveStream liveStream) {
        try {
            String liveId = UUID.randomUUID().toString();
            liveStream.setLiveId(liveId);
            liveStream.setCreateTime(LocalDateTime.now());
            liveStream.setUpdateTime(LocalDateTime.now());
            liveStream.setStatus("stopped");
            liveStream.setViewerCount(0);
            
            // 自动检测直播流类型
            if (liveStream.getLiveUrl().contains("dahua")) {
                liveStream.setLiveType("dahua");
            } else if (liveStream.getLiveUrl().contains("hikvision")) {
                liveStream.setLiveType("hikvision");
            } else {
                liveStream.setLiveType("other");
            }
            
            liveStreamMap.put(liveId, liveStream);
            log.info("添加直播流成功: {}", liveStream);
            return true;
        } catch (Exception e) {
            log.error("添加直播流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean updateLiveStream(LiveStream liveStream) {
        try {
            if (liveStreamMap.containsKey(liveStream.getLiveId())) {
                LiveStream existingStream = liveStreamMap.get(liveStream.getLiveId());
                existingStream.setLiveName(liveStream.getLiveName());
                existingStream.setLiveUrl(liveStream.getLiveUrl());
                existingStream.setProtocol(liveStream.getProtocol());
                existingStream.setUpdateTime(LocalDateTime.now());
                
                liveStreamMap.put(liveStream.getLiveId(), existingStream);
                log.info("更新直播流成功: {}", existingStream);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("更新直播流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean deleteLiveStream(String liveId) {
        try {
            if (liveStreamMap.containsKey(liveId)) {
                // 先停止直播流
                stopLiveStream(liveId);
                liveStreamMap.remove(liveId);
                log.info("删除直播流成功: {}", liveId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("删除直播流失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean startLiveStream(String liveId) {
        try {
            if (liveStreamMap.containsKey(liveId)) {
                LiveStream liveStream = liveStreamMap.get(liveId);
                liveStream.setStatus("running");
                liveStream.setStartTime(LocalDateTime.now());
                liveStreamMap.put(liveId, liveStream);
                log.info("开始直播成功: {}", liveId);
                
                // TODO: 集成开源直播组件实现实际的直播开始逻辑
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("开始直播失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public boolean stopLiveStream(String liveId) {
        try {
            if (liveStreamMap.containsKey(liveId)) {
                LiveStream liveStream = liveStreamMap.get(liveId);
                liveStream.setStatus("stopped");
                liveStream.setEndTime(LocalDateTime.now());
                liveStreamMap.put(liveId, liveStream);
                log.info("停止直播成功: {}", liveId);
                
                // TODO: 集成开源直播组件实现实际的直播停止逻辑
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("停止直播失败: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public String getLiveStreamUrl(String liveId) {
        try {
            if (liveStreamMap.containsKey(liveId)) {
                LiveStream liveStream = liveStreamMap.get(liveId);
                if ("running".equals(liveStream.getStatus())) {
                    // TODO: 集成开源直播组件生成实际的观看地址
                    return liveStream.getLiveUrl();
                } else {
                    log.warn("直播流未运行: {}", liveId);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            log.error("获取直播观看地址失败: {}", e.getMessage(), e);
            return null;
        }
    }
}
