package cc.flyflow.alg.service.impl;

import cc.flyflow.alg.entity.AlgCategory;
import cc.flyflow.alg.mapper.AlgCategoryMapper;
import cc.flyflow.alg.service.IAlgCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 算法分类Service实现
 */
@Service
public class AlgCategoryServiceImpl extends ServiceImpl<AlgCategoryMapper, AlgCategory> implements IAlgCategoryService {
    
    @Override
    public List<AlgCategory> getCategoryTree() {
        // 获取所有分类
        List<AlgCategory> allCategories = baseMapper.selectList(null);
        
        // 构建父ID到子分类的映射
        Map<Long, List<AlgCategory>> parentMap = allCategories.stream()
                .filter(category -> category.getParentId() != null)
                .collect(Collectors.groupingBy(AlgCategory::getParentId));
        
        // 构建分类树
        List<AlgCategory> rootCategories = allCategories.stream()
                .filter(category -> category.getParentId() == null)
                .collect(Collectors.toList());
        
        buildCategoryTree(rootCategories, parentMap);
        
        return rootCategories;
    }
    
    private void buildCategoryTree(List<AlgCategory> categories, Map<Long, List<AlgCategory>> parentMap) {
        for (AlgCategory category : categories) {
            List<AlgCategory> children = parentMap.get(category.getId());
            if (children != null && !children.isEmpty()) {
                category.setChildren(children);
                buildCategoryTree(children, parentMap);
            }
        }
    }
    
    @Override
    public List<AlgCategory> getChildrenByParentId(Long parentId) {
        LambdaQueryWrapper<AlgCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AlgCategory::getParentId, parentId);
        queryWrapper.eq(AlgCategory::getStatus, 1);
        queryWrapper.orderByAsc(AlgCategory::getSort);
        
        return baseMapper.selectList(queryWrapper);
    }
}
