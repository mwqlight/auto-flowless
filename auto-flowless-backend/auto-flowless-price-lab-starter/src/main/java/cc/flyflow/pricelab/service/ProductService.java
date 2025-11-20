package cc.flyflow.pricelab.service;

import cc.flyflow.pricelab.entity.Product;
import java.util.List;

public interface ProductService {
    /**
     * 根据关键词搜索商品并比价
     * @param keyword 搜索关键词
     * @return 商品列表
     */
    List<Product> compareProducts(String keyword);
    
    /**
     * 保存商品信息
     * @param product 商品信息
     * @return 商品信息
     */
    Product saveProduct(Product product);
    
    /**
     * 根据ID获取商品信息
     * @param id 商品ID
     * @return 商品信息
     */
    Product getProductById(Long id);
}
