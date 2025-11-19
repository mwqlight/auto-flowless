package cc.flyflow.avstream.service;

import cc.flyflow.avstream.entity.VideoStream;
import java.util.List;

/**
 * 视频流服务接口
 */
public interface VideoStreamService {
    
    /**
     * 获取所有视频流
     * @return 视频流列表
     */
    List<VideoStream> getAllVideoStreams();
    
    /**
     * 根据ID获取视频流
     * @param streamId 视频流ID
     * @return 视频流信息
     */
    VideoStream getVideoStreamById(String streamId);
    
    /**
     * 添加视频流
     * @param videoStream 视频流信息
     * @return 添加结果
     */
    boolean addVideoStream(VideoStream videoStream);
    
    /**
     * 更新视频流
     * @param videoStream 视频流信息
     * @return 更新结果
     */
    boolean updateVideoStream(VideoStream videoStream);
    
    /**
     * 删除视频流
     * @param streamId 视频流ID
     * @return 删除结果
     */
    boolean deleteVideoStream(String streamId);
    
    /**
     * 播放视频流
     * @param streamId 视频流ID
     * @return 播放结果
     */
    boolean playVideoStream(String streamId);
    
    /**
     * 暂停视频流
     * @param streamId 视频流ID
     * @return 暂停结果
     */
    boolean pauseVideoStream(String streamId);
    
    /**
     * 快进视频流
     * @param streamId 视频流ID
     * @param seconds 快进秒数
     * @return 快进结果
     */
    boolean fastForwardVideoStream(String streamId, long seconds);
    
    /**
     * 快退视频流
     * @param streamId 视频流ID
     * @param seconds 快退秒数
     * @return 快退结果
     */
    boolean fastBackwardVideoStream(String streamId, long seconds);
    
    /**
     * 停止视频流
     * @param streamId 视频流ID
     * @return 停止结果
     */
    boolean stopVideoStream(String streamId);
}
