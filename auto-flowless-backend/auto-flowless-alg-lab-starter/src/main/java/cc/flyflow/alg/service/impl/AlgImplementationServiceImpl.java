package cc.flyflow.alg.service.impl;

import cc.flyflow.alg.entity.AlgImplementation;
import cc.flyflow.alg.mapper.AlgImplementationMapper;
import cc.flyflow.alg.service.IAlgImplementationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 算法实现Service实现
 */
@Service
public class AlgImplementationServiceImpl extends ServiceImpl<AlgImplementationMapper, AlgImplementation> implements IAlgImplementationService {
    
    @Override
    public List<AlgImplementation> getImplsByAlgId(Long algId) {
        LambdaQueryWrapper<AlgImplementation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AlgImplementation::getAlgId, algId);
        queryWrapper.eq(AlgImplementation::getStatus, 1);
        queryWrapper.orderByAsc(AlgImplementation::getSort);
        
        return baseMapper.selectList(queryWrapper);
    }
    
    @Override
    public AlgImplementation getImplDetail(Long implId) {
        return baseMapper.selectById(implId);
    }
}
