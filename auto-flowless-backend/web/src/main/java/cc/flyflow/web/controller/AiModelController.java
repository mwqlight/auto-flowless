package cc.flyflow.web.controller;

import cc.flyflow.biz.entity.AiModel;
import cc.flyflow.biz.service.IAiModelService;
import cc.flyflow.common.dto.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * AI模型Controller
 *
 * @author flyflow
 * @since 2023-07-10
 */
@RestController
@RequestMapping("/ai/model")
@Api(tags = "AI模型管理")
public class AiModelController extends BaseController {

    @Autowired
    private IAiModelService aiModelService;

    /**
     * 查询AI模型列表
     */
    @GetMapping("/list")
    @ApiOperation("查询AI模型列表")
    public R<IPage<AiModel>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,AiModel aiModel) {
        Page<AiModel> page = new Page<>(pageNum, pageSize);
        IPage<AiModel> list = aiModelService.selectAiModelList(page, aiModel);
        return R.success(list);
    }

    /**
     * 获取AI模型详细信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("获取AI模型详细信息")
    public R<AiModel> info(@PathVariable("id") Long id) {
        AiModel aiModel = aiModelService.selectAiModelById(id);
        return R.success(aiModel);
    }

    /**
     * 新增AI模型
     */
    @PostMapping("/add")
    @ApiOperation("新增AI模型")
    public R<Integer> add(@RequestBody AiModel aiModel) {
        int result = aiModelService.insertAiModel(aiModel);
        return R.success(result);
    }

    /**
     * 修改AI模型
     */
    @PostMapping("/edit")
    @ApiOperation("修改AI模型")
    public R<Integer> edit(@RequestBody AiModel aiModel) {
        int result = aiModelService.updateAiModel(aiModel);
        return R.success(result);
    }

    /**
     * 删除AI模型
     */
    @PostMapping("/delete")
    @ApiOperation("删除AI模型")
    public R<Integer> delete(@RequestBody Long[] ids) {
        int result = aiModelService.deleteAiModelByIds(ids);
        return R.success(result);
    }

    /**
     * 获取默认启用的AI模型
     */
    @GetMapping("/default")
    @ApiOperation("获取默认启用的AI模型")
    public R<AiModel> getDefault() {
        AiModel aiModel = aiModelService.getDefaultEnabledModel();
        return R.success(aiModel);
    }
}
