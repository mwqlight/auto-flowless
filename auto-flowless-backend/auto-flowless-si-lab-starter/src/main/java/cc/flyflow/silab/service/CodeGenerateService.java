package cc.flyflow.silab.service;

import cc.flyflow.silab.generator.CodeGenerator;
import cc.flyflow.silab.model.GenerateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeGenerateService {
    
    @Autowired
    private CodeGenerator codeGenerator;

    public void generateCode(GenerateConfig config) {
        // TODO: 验证配置参数
        codeGenerator.generate(config);
    }
    
    public List<String> getSupportedDatabases() {
        return codeGenerator.getSupportedDatabases();
    }
    
    public List<String> getTableNames(String dbUrl, String username, String password) {
        // TODO: 实现获取数据库表名列表的逻辑
        return null;
    }
}
