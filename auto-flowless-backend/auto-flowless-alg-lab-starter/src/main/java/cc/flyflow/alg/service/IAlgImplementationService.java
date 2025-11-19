package cc.flyflow.alg.service;

import cc.flyflow.alg.entity.AlgImplementation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 算法实现Service
 */
public interface IAlgImplementationService extends IService<AlgImplementation> {
    
    /**
     * 根据算法ID获取实现列表
     * @param algId 算法ID
     * @return 实现列表
     */
    List<AlgImplementation> getImplsByAlgId(Long algId);
    
    /**
     * 根据实现ID获取实现详情
     * @param implId 实现ID
     * @return 实现详情
     */
    AlgImplementation getImplDetail(Long implId);
}
