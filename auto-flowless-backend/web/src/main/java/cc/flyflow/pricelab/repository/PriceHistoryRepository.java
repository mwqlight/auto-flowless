package cc.flyflow.pricelab.repository;

import cc.flyflow.pricelab.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    
    /**
     * 根据商品ID查询价格历史
     * @param productId 商品ID
     * @return 价格历史列表
     */
    List<PriceHistory> findByProductIdOrderByRecordTimeDesc(Long productId);
    
    /**
     * 根据商品ID和平台查询价格历史
     * @param productId 商品ID
     * @param platform 平台
     * @return 价格历史列表
     */
    List<PriceHistory> findByProductIdAndPlatformOrderByRecordTimeDesc(Long productId, String platform);
}
