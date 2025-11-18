package cc.flyflow.im.config;

import cc.flyflow.im.netty.IMNettyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:im-server.properties")
public class IMServerConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public IMNettyServer imNettyServer() {
        return new IMNettyServer();
    }
}
