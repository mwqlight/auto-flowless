package cc.flyflow.crm.controller;

import cc.flyflow.crm.entity.CrmCustomer;
import cc.flyflow.crm.service.CrmCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户信息Controller
 * @author flyflow
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/crm/customer")
@Api(tags = "客户信息管理")
public class CrmCustomerController {

    @Autowired
    private CrmCustomerService crmCustomerService;

    /**
     * 查询客户信息列表
     * @param crmCustomer 客户信息
     * @param page 页码
     * @param limit 每页数量
     * @return 客户信息列表
     */
    @GetMapping("/list")
    @ApiOperation("查询客户信息列表")
    public List<CrmCustomer> list(CrmCustomer crmCustomer, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Page<CrmCustomer> pageParam = new Page<>(page, limit);
        QueryWrapper<CrmCustomer> queryWrapper = new QueryWrapper<>(crmCustomer);
        return crmCustomerService.page(pageParam, queryWrapper).getRecords();
    }

    /**
     * 获取客户信息详细信息
     * @param id 客户信息ID
     * @return 客户信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取客户信息详细信息")
    public CrmCustomer getInfo(@PathVariable("id") Long id) {
        return crmCustomerService.selectCrmCustomerById(id);
    }

    /**
     * 新增客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    @PostMapping
    @ApiOperation("新增客户信息")
    public int add(@RequestBody CrmCustomer crmCustomer) {
        return crmCustomerService.insertCrmCustomer(crmCustomer);
    }

    /**
     * 修改客户信息
     * @param crmCustomer 客户信息
     * @return 结果
     */
    @PutMapping
    @ApiOperation("修改客户信息")
    public int edit(@RequestBody CrmCustomer crmCustomer) {
        return crmCustomerService.updateCrmCustomer(crmCustomer);
    }

    /**
     * 删除客户信息
     * @param id 客户信息ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除客户信息")
    public int remove(@PathVariable("id") Long id) {
        return crmCustomerService.deleteCrmCustomerById(id);
    }

    /**
     * 批量删除客户信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除客户信息")
    public int batchRemove(@RequestParam("ids") String ids) {
        return crmCustomerService.deleteCrmCustomerByIds(ids);
    }

    /**
     * 自动回收超期未跟进的客户到公海池
     * @return 回收的客户数量
     */
    @PostMapping("/autoRecycle")
    @ApiOperation("自动回收超期未跟进的客户到公海池")
    public int autoRecycle() {
        return crmCustomerService.autoRecycleOverdueCustomers();
    }

}