package cc.flyflow.crm.controller;

import cc.flyflow.crm.entity.CrmClue;
import cc.flyflow.crm.service.CrmClueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 线索信息Controller
 * @author flyflow
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/crm/clue")
@Api(tags = "线索信息管理")
public class CrmClueController {

    @Autowired
    private CrmClueService crmClueService;

    /**
     * 查询线索信息列表
     * @param crmClue 线索信息
     * @param page 页码
     * @param limit 每页数量
     * @return 线索信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询线索信息列表")
    public List<CrmClue> list(CrmClue crmClue, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Page<CrmClue> pageParam = new Page<>(page, limit);
        QueryWrapper<CrmClue> queryWrapper = new QueryWrapper<>(crmClue);
        return crmClueService.page(pageParam, queryWrapper).getRecords();
    }

    /**
     * 获取线索信息详细信息
     * @param id 线索信息ID
     * @return 线索信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取线索信息详细信息")
    public CrmClue getInfo(@PathVariable("id") Long id) {
        return crmClueService.selectCrmClueById(id);
    }

    /**
     * 新增线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    @PostMapping
    @ApiOperation("新增线索信息")
    public int add(@RequestBody CrmClue crmClue) {
        return crmClueService.insertCrmClue(crmClue);
    }

    /**
     * 修改线索信息
     * @param crmClue 线索信息
     * @return 结果
     */
    @PutMapping
    @ApiOperation("修改线索信息")
    public int edit(@RequestBody CrmClue crmClue) {
        return crmClueService.updateCrmClue(crmClue);
    }

    /**
     * 删除线索信息
     * @param id 线索信息ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除线索信息")
    public int remove(@PathVariable("id") Long id) {
        return crmClueService.deleteCrmClueById(id);
    }

    /**
     * 批量删除线索信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除线索信息")
    public int batchRemove(@RequestParam("ids") String ids) {
        return crmClueService.deleteCrmClueByIds(ids);
    }

}
