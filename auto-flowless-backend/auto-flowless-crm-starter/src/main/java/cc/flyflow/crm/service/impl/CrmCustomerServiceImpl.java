package cc.flyflow.crm.service.impl;

import cc.flyflow.crm.entity.CrmCustomer;
import cc.flyflow.crm.mapper.CrmCustomerMapper;
import cc.flyflow.crm.service.CrmCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户信息Service实现类
 * @author flyflow
 * @since 2024-05-20
 */
@Service
public class CrmCustomerServiceImpl extends ServiceImpl<CrmCustomerMapper, CrmCustomer> implements CrmCustomerService {

    /**
     * 查询客户信息
     * @param id 客户信息ID
     * @return 客户信息
     */
    @Override
    public CrmCustomer selectCrmCustomerById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询客户信息列表
     * @param crmCustomer 客户信息
     * @return 客户信息集合
     */
    @Override
    public List<CrmCustomer> selectCrmCustomerList(CrmCustomer crmCustomer) {
        QueryWrapper<CrmCustomer> queryWrapper = new QueryWrapper<>(crmCustomer);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertCrmCustomer(CrmCustomer crmCustomer) {
        crmCustomer.setCreateTime(LocalDateTime.now());
        crmCustomer.setUpdateTime(LocalDateTime.now());
        crmCustomer.setIsPublicPool(0); // 默认不在公海池
        crmCustomer.setDelFlag(0); // 默认未删除
        return baseMapper.insert(crmCustomer);
    }

    /**
     * 修改客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updateCrmCustomer(CrmCustomer crmCustomer) {
        crmCustomer.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(crmCustomer);
    }

    /**
     * 批量删除客户信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmCustomerByIds(String ids) {
        return baseMapper.deleteBatchIds(List.of(ids.split(",")));
    }

    /**
     * 删除客户信息
     * @param id 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteCrmCustomerById(Long id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 自动回收超期未跟进的客户到公海池
     * @return 回收的客户数量
     */
    @Override
    public int autoRecycleOverdueCustomers() {
        // 查询超期未跟进的客户（这里假设超期时间为30天，可以根据实际需求调整）
        LocalDateTime overdueTime = LocalDateTime.now().minusDays(30);
        QueryWrapper<CrmCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_public_pool", 0) // 不在公海池
                .lt("last_follow_time", overdueTime) // 最后跟进时间早于30天前
                .eq("del_flag", 0); // 未删除

        // 将这些客户回收到公海池
        CrmCustomer updateCustomer = new CrmCustomer();
        updateCustomer.setIsPublicPool(1); // 标记为在公海池
        updateCustomer.setManagerId(null); // 清空客户经理
        updateCustomer.setManagerName(null); // 清空客户经理名称
        updateCustomer.setUpdateTime(LocalDateTime.now());

        return baseMapper.update(updateCustomer, queryWrapper);
    }

}