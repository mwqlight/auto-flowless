package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.entity.AIModel;
import cc.flyflow.biz.mapper.AIModelMapper;
import cc.flyflow.biz.service.IAIModelService;
import cc.flyflow.common.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AI模型Service实现类
 * @author Vincent
 * @since 2023-12-01
 */
@Service
public class AIModelServiceImpl extends ServiceImpl<AIModelMapper, AIModel> implements IAIModelService {

    @Override
    public R<List<AIModel>> queryAll() {
        List<AIModel> list = this.list();
        return R.success(list);
    }

    @Override
    public R<AIModel> queryById(Long id) {
        AIModel aiModel = this.getById(id);
        return R.success(aiModel);
    }

    @Override
    public R create(AIModel aiModel) {
        boolean result = this.save(aiModel);
        return result ? R.success() : R.fail("新增失败");
    }

    @Override
    public R update(AIModel aiModel) {
        boolean result = this.updateById(aiModel);
        return result ? R.success() : R.fail("更新失败");
    }

    @Override
    public R delete(Long id) {
        boolean result = this.removeById(id);
        return result ? R.success() : R.fail("删除失败");
    }

    @Override
    public R enable(Long id, Integer status) {
        AIModel aiModel = this.getById(id);
        if (aiModel == null) {
            return R.fail("AI模型不存在");
        }
        aiModel.setStatus(status);
        boolean result = this.updateById(aiModel);
        return result ? R.success() : R.fail("操作失败");
    }
}
