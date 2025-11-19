package cc.flyflow.avstream.service;

import cc.flyflow.avstream.entity.LiveStream;
import java.util.List;

/**
 * 直播流服务接口
 */
public interface LiveStreamService {
    
    /**
     * 获取所有直播流
     * @return 直播流列表
     */
    List<LiveStream> getAllLiveStreams();
    
    /**
     * 根据ID获取直播流
     * @param liveId 直播流ID
     * @return 直播流信息
     */
    LiveStream getLiveStreamById(String liveId);
    
    /**
     * 添加直播流
     * @param liveStream 直播流信息
     * @return 添加结果
     */
    boolean addLiveStream(LiveStream liveStream);
    
    /**
     * 更新直播流
     * @param liveStream 直播流信息
     * @return 更新结果
     */
    boolean updateLiveStream(LiveStream liveStream);
    
    /**
     * 删除直播流
     * @param liveId 直播流ID
     * @return 删除结果
     */
    boolean deleteLiveStream(String liveId);
    
    /**
     * 开始直播
     * @param liveId 直播流ID
     * @return 开始结果
     */
    boolean startLiveStream(String liveId);
    
    /**
     * 停止直播
     * @param liveId 直播流ID
     * @return 停止结果
     */
    boolean stopLiveStream(String liveId);
    
    /**
     * 获取直播流观看地址
     * @param liveId 直播流ID
     * @return 观看地址
     */
    String getLiveStreamUrl(String liveId);
}
