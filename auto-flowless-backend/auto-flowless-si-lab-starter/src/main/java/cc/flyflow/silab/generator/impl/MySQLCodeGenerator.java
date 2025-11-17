package cc.flyflow.silab.generator.impl;

import cc.flyflow.silab.generator.CodeGenerator;
import cc.flyflow.silab.model.GenerateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MySQLCodeGenerator implements CodeGenerator {

    @Override
    public void generate(GenerateConfig config) {
        // TODO: 实现MySQL代码生成逻辑
        log.info("开始生成MySQL代码，配置：{}", config);
        
        try {
            // 1. 建立数据库连接
            Connection connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUsername(), config.getDbPassword());
            
            // 2. 获取表结构
            for (String tableName : config.getTableNames()) {
                getTableStructure(connection, tableName);
            }
            
            // 3. 生成实体类
            // 4. 生成Mapper接口
            // 5. 生成Service类
            // 6. 生成Controller类
            
            connection.close();
        } catch (SQLException e) {
            log.error("数据库操作失败", e);
        }
    }

    private void getTableStructure(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        
        // 获取表信息
        ResultSet tableResultSet = metaData.getTables(null, null, tableName, new String[] {"TABLE"});
        if (tableResultSet.next()) {
            log.info("表名：{}", tableResultSet.getString("TABLE_NAME"));
            log.info("表注释：{}", tableResultSet.getString("REMARKS"));
        }
        
        // 获取列信息
        ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
        while (columnResultSet.next()) {
            String columnName = columnResultSet.getString("COLUMN_NAME");
            String columnType = columnResultSet.getString("TYPE_NAME");
            int dataSize = columnResultSet.getInt("COLUMN_SIZE");
            String remarks = columnResultSet.getString("REMARKS");
            String isNullable = columnResultSet.getString("IS_NULLABLE");
            String isAutoIncrement = columnResultSet.getString("IS_AUTOINCREMENT");
            
            log.info("列名：{}, 类型：{}, 长度：{}, 注释：{}, 是否 nullable：{}, 是否自增：{}", 
                columnName, columnType, dataSize, remarks, isNullable, isAutoIncrement);
        }
        
        tableResultSet.close();
        columnResultSet.close();
    }

    @Override
    public List<String> getSupportedDatabases() {
        List<String> supportedDatabases = new ArrayList<>();
        supportedDatabases.add("MySQL");
        return supportedDatabases;
    }
}
