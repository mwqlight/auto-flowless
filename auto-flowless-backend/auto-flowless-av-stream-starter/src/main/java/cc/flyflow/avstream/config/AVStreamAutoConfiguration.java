package cc.flyflow.avstream.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 视频流自动配置类
 */
@Configuration
@ConditionalOnClass(name = "cc.flyflow.avstream.controller.VideoStreamController")
@ComponentScan(basePackages = "cc.flyflow.avstream")
public class AVStreamAutoConfiguration {
    // 自动配置类，用于扫描和加载所有视频流相关组件
}
