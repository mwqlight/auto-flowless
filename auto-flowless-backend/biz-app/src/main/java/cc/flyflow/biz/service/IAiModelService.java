package cc.flyflow.biz.service;

import cc.flyflow.biz.entity.AiModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * AI模型Service接口
 *
 * @author flyflow
 * @since 2023-07-10
 */
public interface IAiModelService extends IService<AiModel> {

    /**
     * 查询AI模型
     *
     * @param id AI模型主键
     * @return AI模型
     */
    AiModel selectAiModelById(Long id);

    /**
     * 查询AI模型列表
     *
     * @param page 分页参数
     * @param aiModel AI模型
     * @return AI模型集合
     */
    IPage<AiModel> selectAiModelList(Page<AiModel> page, AiModel aiModel);

    /**
     * 新增AI模型
     *
     * @param aiModel AI模型
     * @return 结果
     */
    int insertAiModel(AiModel aiModel);

    /**
     * 修改AI模型
     *
     * @param aiModel AI模型
     * @return 结果
     */
    int updateAiModel(AiModel aiModel);

    /**
     * 批量删除AI模型
     *
     * @param ids 需要删除的AI模型主键集合
     * @return 结果
     */
    int deleteAiModelByIds(Long[] ids);

    /**
     * 删除AI模型信息
     *
     * @param id AI模型主键
     * @return 结果
     */
    int deleteAiModelById(Long id);

    /**
     * 获取默认启用的AI模型
     *
     * @return AI模型
     */
    AiModel getDefaultEnabledModel();
}
