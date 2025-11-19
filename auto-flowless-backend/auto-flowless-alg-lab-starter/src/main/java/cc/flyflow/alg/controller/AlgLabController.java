package cc.flyflow.alg.controller;

import cc.flyflow.alg.entity.AlgCategory;
import cc.flyflow.alg.entity.Algorithm;
import cc.flyflow.alg.entity.AlgImplementation;
import cc.flyflow.alg.service.IAlgCategoryService;
import cc.flyflow.alg.service.IAlgorithmService;
import cc.flyflow.alg.service.IAlgImplementationService;
import cc.flyflow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 算法实验室Controller
 */
@RestController
@RequestMapping("/alg-lab")
@Api(tags = "算法实验室")
public class AlgLabController {
    
    @Autowired
    private IAlgCategoryService algCategoryService;
    
    @Autowired
    private IAlgorithmService algorithmService;
    
    @Autowired
    private IAlgImplementationService algImplementationService;
    
    @ApiOperation("获取算法分类树")
    @GetMapping("/categories/tree")
    public R<List<AlgCategory>> getCategoryTree() {
        List<AlgCategory> categoryTree = algCategoryService.getCategoryTree();
        return R.success(categoryTree);
    }
    
    @ApiOperation("根据父ID获取子分类")
    @GetMapping("/categories/children/{parentId}")
    public R<List<AlgCategory>> getChildrenByParentId(@PathVariable Long parentId) {
        List<AlgCategory> children = algCategoryService.getChildrenByParentId(parentId);
        return R.success(children);
    }
    
    @ApiOperation("根据分类ID获取算法列表")
    @GetMapping("/algorithms/{categoryId}")
    public R<List<Algorithm>> getAlgsByCategoryId(@PathVariable Long categoryId) {
        List<Algorithm> algorithms = algorithmService.getAlgsByCategoryId(categoryId);
        return R.success(algorithms);
    }
    
    @ApiOperation("根据算法ID获取算法详情")
    @GetMapping("/algorithm/{algId}")
    public R<Algorithm> getAlgDetail(@PathVariable Long algId) {
        Algorithm algorithm = algorithmService.getAlgDetail(algId);
        return R.success(algorithm);
    }
    
    @ApiOperation("根据算法ID获取实现列表")
    @GetMapping("/implementations/{algId}")
    public R<List<AlgImplementation>> getImplsByAlgId(@PathVariable Long algId) {
        List<AlgImplementation> implementations = algImplementationService.getImplsByAlgId(algId);
        return R.success(implementations);
    }
    
    @ApiOperation("根据实现ID获取实现详情")
    @GetMapping("/implementation/{implId}")
    public R<AlgImplementation> getImplDetail(@PathVariable Long implId) {
        AlgImplementation implementation = algImplementationService.getImplDetail(implId);
        return R.success(implementation);
    }
}
