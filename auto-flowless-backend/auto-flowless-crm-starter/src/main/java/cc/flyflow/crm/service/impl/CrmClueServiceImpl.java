package cc.flyflow.crm.service.impl;

import cc.flyflow.crm.entity.CrmClue;
import cc.flyflow.crm.mapper.CrmClueMapper;
import cc.flyflow.crm.service.CrmClueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 线索信息Service业务层处理
 * @author flyflow
 * @since 2024-05-20
 */
@Service
public class CrmClueServiceImpl extends ServiceImpl<CrmClueMapper, CrmClue> implements CrmClueService {

    /**
     * 查询线索信息
     * @param id 线索信息ID
     * @return 线索信息
     */
    @Override
    public CrmClue selectCrmClueById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询线索信息列表
     * @param crmClue 线索信息
     * @return 线索信息集合
     */
    @Override
    public List<CrmClue> selectCrmClueList(CrmClue crmClue) {
        QueryWrapper<CrmClue> queryWrapper = new QueryWrapper<>(crmClue);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    @Override
    public int insertCrmClue(CrmClue crmClue) {
        crmClue.setCreateTime(LocalDateTime.now());
        crmClue.setUpdateTime(LocalDateTime.now());
        crmClue.setDelFlag(0); // 默认未删除
        return baseMapper.insert(crmClue);
    }

    /**
     * 修改线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    @Override
    public int updateCrmClue(CrmClue crmClue) {
        crmClue.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(crmClue);
    }

    /**
     * 批量删除线索信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueByIds(String ids) {
        return baseMapper.deleteBatchIds(List.of(ids.split(",")));
    }

    /**
     * 删除线索信息
     * @param id 线索信息ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueById(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public void autoRecycleOverdueClues() {
        // 查询30天未跟进的线索
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        QueryWrapper<CrmClue> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("follow_up_time", thirtyDaysAgo)
                .eq("is_public_pool", false);
        List<CrmClue> overdueClues = baseMapper.selectList(queryWrapper);

        // 将超期未跟进的线索回收到公海池
        for (CrmClue clue : overdueClues) {
            clue.setIsPublicPool(true);
            clue.setLastFollowUpUserId(null);
            clue.setLastFollowUpTime(null);
            baseMapper.updateById(clue);
        }
    }

}
