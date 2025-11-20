package cc.flyflow.pricelab.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Component
public class MenuInitializer implements CommandLineRunner {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    
    @Override
    public void run(String... args) throws Exception {
        // 检查数据库是否为flyflow_os
        if (datasourceUrl.contains("flyflow_os")) {
            // 检查菜单是否已存在
            String checkSql = "SELECT COUNT(*) FROM sys_menu WHERE path = 'price-lab'";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class);
            
            if (count == null || count == 0) {
                // 执行初始化SQL
                executeSqlFile("classpath:init-menu.sql");
                log.info("商城比价菜单初始化成功");
            } else {
                log.info("商城比价菜单已存在");
            }
        } else {
            log.info("当前数据库不是flyflow_os，跳过菜单初始化");
        }
    }
    
    private void executeSqlFile(String sqlFile) throws IOException {
        ClassPathResource resource = new ClassPathResource(sqlFile);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // 跳过注释和空行
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                sqlBuilder.append(line);
                
                // 如果是完整的SQL语句，执行
                if (line.endsWith(";")) {
                    String sql = sqlBuilder.toString();
                    try {
                        if (sql.toUpperCase().startsWith("INSERT")) {
                            jdbcTemplate.update(sql);
                        } else if (sql.toUpperCase().startsWith("SELECT")) {
                            // 忽略SELECT语句
                        } else {
                            jdbcTemplate.execute(sql);
                        }
                    } catch (Exception e) {
                        log.error("执行SQL失败: {}", sql, e);
                    }
                    sqlBuilder.setLength(0);
                }
            }
            
            // 执行剩余的SQL语句
            if (sqlBuilder.length() > 0) {
                String sql = sqlBuilder.toString();
                try {
                    jdbcTemplate.execute(sql);
                } catch (Exception e) {
                    log.error("执行SQL失败: {}", sql, e);
                }
            }
        }
    }
}
