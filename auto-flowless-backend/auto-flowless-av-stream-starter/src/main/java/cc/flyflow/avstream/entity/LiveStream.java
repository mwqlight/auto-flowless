package cc.flyflow.avstream.entity;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 直播流实体类
 */
@Data
public class LiveStream implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 直播流ID
     */
    private String liveId;
    
    /**
     * 直播流名称
     */
    private String liveName;
    
    /**
     * 直播流URL
     */
    private String liveUrl;
    
    /**
     * 直播流协议 (rtsp/rtmp/flv/hls)
     */
    private String protocol;
    
    /**
     * 直播流状态 (running/stopped)
     */
    private String status;
    
    /**
     * 直播开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 直播结束时间
     */
    private LocalDateTime endTime;
    
    /**
     * 观看人数
     */
    private Integer viewerCount;
    
    /**
     * 直播流类型 (dahua/hikvision/other)
     */
    private String liveType;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
