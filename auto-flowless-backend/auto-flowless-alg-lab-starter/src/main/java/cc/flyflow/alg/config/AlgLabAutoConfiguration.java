package cc.flyflow.alg.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 算法实验室自动配置类
 */
@Configuration
@ConditionalOnClass(AlgLabAutoConfiguration.class)
@ComponentScan(basePackages = "cc.flyflow.alg")
public class AlgLabAutoConfiguration {
}
