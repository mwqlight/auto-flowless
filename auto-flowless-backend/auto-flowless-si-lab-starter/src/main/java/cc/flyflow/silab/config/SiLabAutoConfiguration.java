package cc.flyflow.silab.config;

import cc.flyflow.silab.generator.CodeGenerator;
import cc.flyflow.silab.generator.impl.MySQLCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiLabAutoConfiguration {
    
    @Bean
    public CodeGenerator codeGenerator() {
        return new MySQLCodeGenerator();
    }
}
