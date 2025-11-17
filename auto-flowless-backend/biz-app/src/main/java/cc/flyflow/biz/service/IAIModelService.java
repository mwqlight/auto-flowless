package cc.flyflow.biz.service;

import cc.flyflow.common.dto.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cc.flyflow.biz.entity.AIModel;

import java.util.List;

/**
 * AI模型Service接口
 * @author Vincent
 * @since 2023-12-01
 */
public interface IAIModelService extends IService<AIModel> {

    /**
     * 查询所有AI模型
     * @return AI模型列表
     */
    R<List<AIModel>> queryAll();

    /**
     * 根据ID查询AI模型
     * @param id AI模型ID
     * @return AI模型
     */
    R<AIModel> queryById(Long id);

    /**
     * 新增AI模型
     * @param aiModel AI模型
     * @return 新增结果
     */
    R create(AIModel aiModel);

    /**
     * 更新AI模型
     * @param aiModel AI模型
     * @return 更新结果
     */
    R update(AIModel aiModel);

    /**
     * 删除AI模型
     * @param id AI模型ID
     * @return 删除结果
     */
    R delete(Long id);

    /**
     * 启用/禁用AI模型
     * @param id AI模型ID
     * @param status 状态：0-禁用，1-启用
     * @return 操作结果
     */
    R enable(Long id, Integer status);
}
