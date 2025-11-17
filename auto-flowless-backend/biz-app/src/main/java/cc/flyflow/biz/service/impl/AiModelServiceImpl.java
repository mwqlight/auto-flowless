package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.entity.AiModel;
import cc.flyflow.biz.mapper.AiModelMapper;
import cc.flyflow.biz.service.IAiModelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AI模型Service业务层处理
 *
 * @author flyflow
 * @since 2023-07-10
 */
@Service
public class AiModelServiceImpl extends ServiceImpl<AiModelMapper, AiModel> implements IAiModelService {

    @Autowired
    private AiModelMapper aiModelMapper;

    /**
     * 查询AI模型
     *
     * @param id AI模型主键
     * @return AI模型
     */
    @Override
    public AiModel selectAiModelById(Long id) {
        return aiModelMapper.selectById(id);
    }

    /**
 * 查询AI模型列表
 *
 * @param page 分页参数
 * @param aiModel AI模型
 * @return AI模型集合
 */
@Override
public IPage<AiModel> selectAiModelList(Page<AiModel> page, AiModel aiModel) {
    LambdaQueryWrapper<AiModel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(aiModel.getId() != null, AiModel::getId, aiModel.getId());
    queryWrapper.like(aiModel.getName() != null, AiModel::getName, aiModel.getName());
    queryWrapper.like(aiModel.getType() != null, AiModel::getType, aiModel.getType());
    queryWrapper.like(aiModel.getVersion() != null, AiModel::getVersion, aiModel.getVersion());
    queryWrapper.eq(aiModel.getStatus() != null, AiModel::getStatus, aiModel.getStatus());
    queryWrapper.orderByDesc(AiModel::getSort);
    queryWrapper.orderByDesc(AiModel::getCreateTime);
    return aiModelMapper.selectPage(page, queryWrapper);
}

/**
 * 新增AI模型
 *
 * @param aiModel AI模型
 * @return 结果
 */
@Override
public int insertAiModel(AiModel aiModel) {
    return aiModelMapper.insert(aiModel);
}

/**
 * 修改AI模型
 *
 * @param aiModel AI模型
 * @return 结果
 */
@Override
public int updateAiModel(AiModel aiModel) {
    return aiModelMapper.updateById(aiModel);
}

/**
 * 批量删除AI模型
 *
 * @param ids 需要删除的AI模型主键集合
 * @return 结果
 */
@Override
public int deleteAiModelByIds(Long[] ids) {
    return aiModelMapper.deleteBatchIds(java.util.Arrays.asList(ids));
}

/**
 * 删除AI模型信息
 *
 * @param id AI模型主键
 * @return 结果
 */
@Override
public int deleteAiModelById(Long id) {
    return aiModelMapper.deleteById(id);
}

/**
 * 获取默认启用的AI模型
 *
 * @return AI模型
 */
@Override
public AiModel getDefaultEnabledModel() {
    LambdaQueryWrapper<AiModel> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(AiModel::getStatus, 1);
    queryWrapper.orderByDesc(AiModel::getSort);
    queryWrapper.orderByDesc(AiModel::getCreateTime);
    queryWrapper.last("limit 1");
    return aiModelMapper.selectOne(queryWrapper);
}
}
