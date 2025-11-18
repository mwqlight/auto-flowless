package cc.flyflow.im.model;

import lombok.Data;

/**
 * IM消息模型
 */
@Data
public class IMMessage {
    
    /**
     * 消息类型
     */
    private String type;
    
    /**
     * 发送者邮箱
     */
    private String from;
    
    /**
     * 接收者邮箱
     */
    private String to;
    
    /**
     * 消息内容
     */
    private String content;
    
    /**
     * 消息时间
     */
    private Long timestamp;
    
    /**
     * 签名
     */
    private String signature;
}
