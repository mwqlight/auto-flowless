package cc.flyflow.crm.service;

import cc.flyflow.crm.entity.CrmOpportunity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商机信息Service接口
 * @author flyflow
 * @since 2024-05-20
 */
public interface CrmOpportunityService extends IService<CrmOpportunity> {

    /**
     * 查询商机信息
     * @param id 商机信息ID
     * @return 商机信息
     */
    CrmOpportunity selectCrmOpportunityById(Long id);

    /**
     * 查询商机信息列表
     * @param crmOpportunity 商机信息
     * @return 商机信息集合
     */
    List<CrmOpportunity> selectCrmOpportunityList(CrmOpportunity crmOpportunity);

    /**
     * 新增商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    int insertCrmOpportunity(CrmOpportunity crmOpportunity);

    /**
     * 修改商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    int updateCrmOpportunity(CrmOpportunity crmOpportunity);

    /**
     * 批量删除商机信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCrmOpportunityByIds(String ids);

    /**
     * 删除商机信息
     * @param id 商机信息ID
     * @return 结果
     */
    int deleteCrmOpportunityById(Long id);

    /**
     * 自动回收超期未跟进的商机
     */
    void autoRecycleOverdueOpportunities();

}
