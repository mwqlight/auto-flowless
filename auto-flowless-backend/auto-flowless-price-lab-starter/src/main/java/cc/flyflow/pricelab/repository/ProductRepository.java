package cc.flyflow.pricelab.repository;

import cc.flyflow.pricelab.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * 根据商品名称和平台查询商品
     * @param productName 商品名称
     * @param platform 平台
     * @return 商品信息
     */
    Product findByProductNameAndPlatform(String productName, String platform);
    
    /**
     * 根据关键词查询商品
     * @param keyword 关键词
     * @return 商品列表
     */
    List<Product> findByProductNameContaining(String keyword);
}
