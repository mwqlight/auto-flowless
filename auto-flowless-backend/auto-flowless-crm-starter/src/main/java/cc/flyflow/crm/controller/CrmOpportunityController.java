package cc.flyflow.crm.controller;

import cc.flyflow.crm.entity.CrmOpportunity;
import cc.flyflow.crm.service.CrmOpportunityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商机信息Controller
 * @author flyflow
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/crm/opportunity")
@Api(tags = "商机信息管理")
public class CrmOpportunityController {

    @Autowired
    private CrmOpportunityService crmOpportunityService;

    /**
     * 查询商机信息列表
     * @param crmOpportunity 商机信息
     * @param page 页码
     * @param limit 每页数量
     * @return 商机信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询商机信息列表")
    public List<CrmOpportunity> list(CrmOpportunity crmOpportunity, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Page<CrmOpportunity> pageParam = new Page<>(page, limit);
        QueryWrapper<CrmOpportunity> queryWrapper = new QueryWrapper<>(crmOpportunity);
        return crmOpportunityService.page(pageParam, queryWrapper).getRecords();
    }

    /**
     * 获取商机信息详细信息
     * @param id 商机信息ID
     * @return 商机信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取商机信息详细信息")
    public CrmOpportunity getInfo(@PathVariable("id") Long id) {
        return crmOpportunityService.selectCrmOpportunityById(id);
    }

    /**
     * 新增商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    @PostMapping
    @ApiOperation("新增商机信息")
    public int add(@RequestBody CrmOpportunity crmOpportunity) {
        return crmOpportunityService.insertCrmOpportunity(crmOpportunity);
    }

    /**
     * 修改商机信息
     * @param crmOpportunity 商机信息
     * @return 结果
     */
    @PutMapping
    @ApiOperation("修改商机信息")
    public int edit(@RequestBody CrmOpportunity crmOpportunity) {
        return crmOpportunityService.updateCrmOpportunity(crmOpportunity);
    }

    /**
     * 删除商机信息
     * @param id 商机信息ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除商机信息")
    public int remove(@PathVariable("id") Long id) {
        return crmOpportunityService.deleteCrmOpportunityById(id);
    }

    /**
     * 批量删除商机信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除商机信息")
    public int batchRemove(@RequestParam("ids") String ids) {
        return crmOpportunityService.deleteCrmOpportunityByIds(ids);
    }

}
