package cc.flyflow.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * CRM模块启动类
 * @author flyflow
 * @since 2024-05-20
 */
@SpringBootApplication
@ComponentScan(basePackages = "cc.flyflow.crm")
@MapperScan(basePackages = "cc.flyflow.crm.mapper")
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

}