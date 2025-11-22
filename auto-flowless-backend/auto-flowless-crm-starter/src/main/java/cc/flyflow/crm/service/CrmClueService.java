package cc.flyflow.crm.service;

import cc.flyflow.crm.entity.CrmClue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 线索信息Service接口
 * @author flyflow
 * @since 2024-05-20
 */
public interface CrmClueService extends IService<CrmClue> {

    /**
     * 查询线索信息
     * @param id 线索信息ID
     * @return 线索信息
     */
    CrmClue selectCrmClueById(Long id);

    /**
     * 查询线索信息列表
     * @param crmClue 线索信息
     * @return 线索信息集合
     */
    List<CrmClue> selectCrmClueList(CrmClue crmClue);

    /**
     * 新增线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    int insertCrmClue(CrmClue crmClue);

    /**
     * 修改线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    int updateCrmClue(CrmClue crmClue);

    /**
     * 批量删除线索信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCrmClueByIds(String ids);

    /**
     * 删除线索信息
     * @param id 线索信息ID
     * @return 结果
     */
    int deleteCrmClueById(Long id);

    /**
     * 自动回收超期未跟进的线索
     */
    void autoRecycleOverdueClues();

}
