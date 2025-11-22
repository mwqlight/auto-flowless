package cc.flyflow.crm.service;

import cc.flyflow.crm.entity.CrmCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 客户信息Service接口
 * @author flyflow
 * @since 2024-05-20
 */
public interface CrmCustomerService extends IService<CrmCustomer> {

    /**
     * 查询客户信息
     * @param id 客户信息ID
     * @return 客户信息
     */
    CrmCustomer selectCrmCustomerById(Long id);

    /**
     * 查询客户信息列表
     * @param crmCustomer 客户信息
     * @return 客户信息集合
     */
    List<CrmCustomer> selectCrmCustomerList(CrmCustomer crmCustomer);

    /**
     * 新增客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    int insertCrmCustomer(CrmCustomer crmCustomer);

    /**
     * 修改客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    int updateCrmCustomer(CrmCustomer crmCustomer);

    /**
     * 批量删除客户信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCrmCustomerByIds(String ids);

    /**
     * 删除客户信息
     * @param id 客户信息ID
     * @return 结果
     */
    int deleteCrmCustomerById(Long id);

    /**
     * 自动回收超期未跟进的客户到公海池
     * @return 回收的客户数量
     */
    int autoRecycleOverdueCustomers();

}