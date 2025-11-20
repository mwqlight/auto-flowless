package cc.flyflow.pricelab.service;

import cc.flyflow.pricelab.entity.Product;
import java.util.List;

public interface SpiderService {
    /**
     * 根据关键词搜索商品
     * @param keyword 搜索关键词
     * @return 商品列表
     */
    List<Product> searchProducts(String keyword);
    
    /**
     * 根据URL获取商品信息
     * @param url 商品URL
     * @return 商品信息
     */
    Product getProductByUrl(String url);
    
    /**
     * 更新商品价格
     * @param productId 商品ID
     * @return 更新后的商品信息
     */
    Product updateProductPrice(Long productId);
}
