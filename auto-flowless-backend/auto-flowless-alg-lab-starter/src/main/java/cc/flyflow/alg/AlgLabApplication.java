package cc.flyflow.alg;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * 算法实验室启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "cc.flyflow.alg")
@EnableConfigurationProperties
public class AlgLabApplication {
}
