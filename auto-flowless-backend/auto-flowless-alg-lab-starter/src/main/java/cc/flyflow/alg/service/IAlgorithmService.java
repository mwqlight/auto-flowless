package cc.flyflow.alg.service;

import cc.flyflow.alg.entity.Algorithm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 算法Service
 */
public interface IAlgorithmService extends IService<Algorithm> {
    
    /**
     * 根据分类ID获取算法列表
     * @param categoryId 分类ID
     * @return 算法列表
     */
    List<Algorithm> getAlgsByCategoryId(Long categoryId);
    
    /**
     * 根据算法ID获取算法详情
     * @param algId 算法ID
     * @return 算法详情
     */
    Algorithm getAlgDetail(Long algId);
}
