package cc.flyflow.im;

import cc.flyflow.im.config.IMConfig;
import cc.flyflow.im.server.IMServer;
import cc.flyflow.im.client.IMClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * IM模块启动类
 */
@Slf4j
@Component
public class IMStarter implements ApplicationRunner {
    
    @Autowired
    private IMConfig imConfig;
    
    @Autowired(required = false)
    private IMServer imServer;
    
    @Autowired(required = false)
    private IMClient imClient;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("IM模块开始启动...");
        log.info("IM配置: {}", imConfig);
        
        // 根据模式启动不同的服务
        String mode = imConfig.getMode();
        if ("server".equals(mode) || "both".equals(mode)) {
            if (imServer != null) {
                imServer.start();
            }
        }
        
        if ("client".equals(mode) || "both".equals(mode)) {
            if (imClient != null) {
                imClient.start();
            }
        }
        
        log.info("IM模块启动完成!");
    }
}
