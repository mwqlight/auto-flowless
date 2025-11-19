package cc.flyflow.alg.service.impl;

import cc.flyflow.alg.entity.Algorithm;
import cc.flyflow.alg.mapper.AlgorithmMapper;
import cc.flyflow.alg.service.IAlgorithmService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 算法Service实现
 */
@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements IAlgorithmService {
    
    @Override
    public List<Algorithm> getAlgsByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Algorithm> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Algorithm::getCategoryId, categoryId);
        queryWrapper.eq(Algorithm::getStatus, 1);
        queryWrapper.orderByAsc(Algorithm::getSort);
        
        return baseMapper.selectList(queryWrapper);
    }
    
    @Override
    public Algorithm getAlgDetail(Long algId) {
        return baseMapper.selectById(algId);
    }
}
