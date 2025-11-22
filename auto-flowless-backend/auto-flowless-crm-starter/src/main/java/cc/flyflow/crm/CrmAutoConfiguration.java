package cc.flyflow.crm;

import cc.flyflow.crm.config.ScheduledTaskConfig;
import cc.flyflow.crm.service.CrmCustomerService;
import cc.flyflow.crm.service.CrmClueService;
import cc.flyflow.crm.service.CrmOpportunityService;
import cc.flyflow.crm.service.impl.CrmCustomerServiceImpl;
import cc.flyflow.crm.service.impl.CrmClueServiceImpl;
import cc.flyflow.crm.service.impl.CrmOpportunityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * CRM模块自动配置类
 * @author flyflow
 * @since 2024-05-20
 */
@Configuration
@Import({ScheduledTaskConfig.class})
public class CrmAutoConfiguration {

    @Bean
    public CrmCustomerService crmCustomerService() {
        return new CrmCustomerServiceImpl();
    }

    @Bean
    public CrmClueService crmClueService() {
        return new CrmClueServiceImpl();
    }

    @Bean
    public CrmOpportunityService crmOpportunityService() {
        return new CrmOpportunityServiceImpl();
    }

}