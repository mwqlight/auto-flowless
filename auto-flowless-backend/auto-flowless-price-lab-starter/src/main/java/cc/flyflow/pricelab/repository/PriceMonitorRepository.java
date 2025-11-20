package cc.flyflow.pricelab.repository;

import cc.flyflow.pricelab.entity.PriceMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceMonitorRepository extends JpaRepository<PriceMonitor, Long> {
    
    /**
     * 根据用户ID查询监控列表
     * @param userId 用户ID
     * @return 监控列表
     */
    List<PriceMonitor> findByUserId(Long userId);
    
    /**
     * 根据用户ID和商品ID查询监控
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 监控信息
     */
    PriceMonitor findByUserIdAndProductId(Long userId, Long productId);
}
