package cc.flyflow.avstream.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视频流实体类
 */
@Data
public class VideoStream implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 视频流ID
     */
    private String streamId;
    
    /**
     * 视频流名称
     */
    private String streamName;
    
    /**
     * 视频流URL
     */
    private String streamUrl;
    
    /**
     * 视频流协议 (rtsp/rtmp)
     */
    private String protocol;
    
    /**
     * 视频流状态 (playing/paused/stopped)
     */
    private String status;
    
    /**
     * 播放时间
     */
    private LocalDateTime playTime;
    
    /**
     * 播放进度 (秒)
     */
    private Long playProgress;
    
    /**
     * 视频流类型 (dahua/hikvision/other)
     */
    private String streamType;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}