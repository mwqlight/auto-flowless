package cc.flyflow.im.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * IM配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "im")
public class IMConfig {
    
    /**
     * 模式：server/client/both
     */
    private String mode = "both";
    
    /**
     * 服务器端口
     */
    private int serverPort = 8888;
    
    /**
     * 客户端端口
     */
    private int clientPort = 9999;
    
    /**
     * 集群数量
     */
    private int clusterSize = 1;
    
    /**
     * RSA公钥
     */
    private String rsaPublicKey;
    
    /**
     * RSA私钥
     */
    private String rsaPrivateKey;
}
