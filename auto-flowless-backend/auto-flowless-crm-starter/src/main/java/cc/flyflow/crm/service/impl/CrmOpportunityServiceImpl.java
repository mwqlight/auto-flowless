package cc.flyflow.crm.service.impl;

import cc.flyflow.crm.entity.CrmOpportunity;
import cc.flyflow.crm.mapper.CrmOpportunityMapper;
import cc.flyflow.crm.service.CrmOpportunityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商机信息Service业务层处理
 * @author flyflow
 * @since 2024-05-20
 */
@Service
public class CrmOpportunityServiceImpl extends ServiceImpl<CrmOpportunityMapper, CrmOpportunity> implements CrmOpportunityService {

    /**
     * 查询商机信息
     * @param id 商机信息ID
     * @return 商机信息
     */
    @Override
    public CrmOpportunity selectCrmOpportunityById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询商机信息列表
     * @param crmOpportunity 商机信息
     * @return 商机信息集合
     */
    @Override
    public List<CrmOpportunity> selectCrmOpportunityList(CrmOpportunity crmOpportunity) {
        QueryWrapper<CrmOpportunity> queryWrapper = new QueryWrapper<>(crmOpportunity);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    @Override
    public int insertCrmOpportunity(CrmOpportunity crmOpportunity) {
        crmOpportunity.setCreateTime(LocalDateTime.now());
        crmOpportunity.setUpdateTime(LocalDateTime.now());
        crmOpportunity.setDelFlag(0); // 默认未删除
        return baseMapper.insert(crmOpportunity);
    }

    /**
     * 修改商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    @Override
    public int updateCrmOpportunity(CrmOpportunity crmOpportunity) {
        crmOpportunity.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(crmOpportunity);
    }

    /**
     * 批量删除商机信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmOpportunityByIds(String ids) {
        return baseMapper.deleteBatchIds(List.of(ids.split(",")));
    }

    /**
     * 删除商机信息
     * @param id 商机信息ID
     * @return 结果
     */
    @Override
    public int deleteCrmOpportunityById(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public void autoRecycleOverdueOpportunities() {
        // 查询30天未跟进的商机
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        QueryWrapper<CrmOpportunity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("follow_up_time", thirtyDaysAgo)
                .eq("is_public_pool", false);
        List<CrmOpportunity> overdueOpportunities = baseMapper.selectList(queryWrapper);

        // 将超期未跟进的商机回收到公海池
        for (CrmOpportunity opportunity : overdueOpportunities) {
            opportunity.setIsPublicPool(true);
            opportunity.setLastFollowUpUserId(null);
            opportunity.setLastFollowUpTime(null);
            baseMapper.updateById(opportunity);
        }
    }

}
