package cc.flyflow.silab.model;

import lombok.Data;
import java.util.List;

@Data
public class GenerateConfig {
    private String sqlFile;
    private String sqlContent;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private List<String> tableNames;
    private String entityPackage;
    private String mapperPackage;
    private String servicePackage;
    private String controllerPackage;
    private String outputPath;
}
