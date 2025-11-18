package cc.flyflow.llm.config;

import org.python.util.PythonInterpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * AI大模型配置类
 * 用于初始化Jython解释器和Spring AI相关配置
 */
@Configuration
public class LLMConfig {

    /**
     * 初始化Jython解释器配置
     */
    @PostConstruct
    public void initJython() {
        // 设置Jython系统属性
        Properties props = System.getProperties();
        // 可以在这里添加Jython相关配置
        props.put("python.console.encoding", "UTF-8");
        props.put("python.path", System.getProperty("java.class.path"));
    }

    /**
     * 创建Jython解释器Bean
     * 
     * @return PythonInterpreter Jython解释器实例
     */
    @Bean
    public PythonInterpreter pythonInterpreter() {
        PythonInterpreter interpreter = new PythonInterpreter();
        // 设置Python模块搜索路径
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append('.')");
        interpreter.exec("sys.path.append('auto-flowless-llm-manager-python')");
        
        return interpreter;
    }
}
