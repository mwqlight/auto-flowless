package cc.flyflow.alg.service;

import cc.flyflow.alg.entity.AlgCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 算法分类Service
 */
public interface IAlgCategoryService extends IService<AlgCategory> {
    
    /**
     * 获取分类树
     * @return 分类树
     */
    List<AlgCategory> getCategoryTree();
    
    /**
     * 根据父ID获取子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<AlgCategory> getChildrenByParentId(Long parentId);
}
