package cc.flyflow.crm.config;

import cc.flyflow.crm.service.CrmCustomerService;
import cc.flyflow.crm.service.CrmClueService;
import cc.flyflow.crm.service.CrmOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置类
 * @author flyflow
 * @since 2024-05-20
 */
@Configuration
@EnableScheduling
public class ScheduledTaskConfig {

    @Autowired
    private CrmCustomerService crmCustomerService;

    @Autowired
    private CrmClueService crmClueService;

    @Autowired
    private CrmOpportunityService crmOpportunityService;

    /**
     * 每天凌晨2点执行客户自动回收任务
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void autoRecycleOverdueCustomers() {
        crmCustomerService.autoRecycleOverdueCustomers();
    }

    /**
     * 每天凌晨2点执行线索自动回收任务
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void autoRecycleOverdueClues() {
        crmClueService.autoRecycleOverdueClues();
    }

    /**
     * 每天凌晨2点执行商机自动回收任务
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void autoRecycleOverdueOpportunities() {
        crmOpportunityService.autoRecycleOverdueOpportunities();
    }

}