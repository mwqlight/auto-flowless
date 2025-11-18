package cc.flyflow.llm;

import cc.flyflow.llm.config.LLMConfig;
import cc.flyflow.llm.controller.LLMController;
import cc.flyflow.llm.service.LLMService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * AI大模型自动配置类
 * 用于Spring Boot自动配置LLM相关组件
 */
@Configuration
@ConditionalOnClass(LLMService.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ComponentScan(basePackages = "cc.flyflow.llm")
@Import(LLMConfig.class)
public class LLMAutoConfiguration {
    // 自动配置类，Spring Boot会自动扫描并配置相关Bean
}
