package cc.flyflow.biz.controller;

import cc.flyflow.biz.entity.AIModel;
import cc.flyflow.biz.service.IAIModelService;
import cc.flyflow.common.dto.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AI模型Controller
 * @author Vincent
 * @since 2023-12-01
 */
@RestController
@RequestMapping("/ai/model")
@Api(tags = "AI模型管理")
public class AIModelController {

    @Autowired
    private IAIModelService aiModelService;

    @ApiOperation("查询所有AI模型")
    @GetMapping("/all")
    public R<List<AIModel>> queryAll() {
        return aiModelService.queryAll();
    }

    @ApiOperation("根据ID查询AI模型")
    @GetMapping("/{id}")
    public R<AIModel> queryById(@PathVariable Long id) {
        return aiModelService.queryById(id);
    }

    @ApiOperation("新增AI模型")
    @PostMapping
    public R create(@RequestBody AIModel aiModel) {
        return aiModelService.create(aiModel);
    }

    @ApiOperation("更新AI模型")
    @PutMapping
    public R update(@RequestBody AIModel aiModel) {
        return aiModelService.update(aiModel);
    }

    @ApiOperation("删除AI模型")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        return aiModelService.delete(id);
    }

    @ApiOperation("启用/禁用AI模型")
    @PutMapping("/enable/{id}")
    public R enable(@PathVariable Long id, @RequestParam Integer status) {
        return aiModelService.enable(id, status);
    }
}
