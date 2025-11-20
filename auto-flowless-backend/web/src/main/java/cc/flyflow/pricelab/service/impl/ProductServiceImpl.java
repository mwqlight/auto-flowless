package cc.flyflow.pricelab.service.impl;

import cc.flyflow.pricelab.entity.Product;
import cc.flyflow.pricelab.repository.ProductRepository;
import cc.flyflow.pricelab.service.ProductService;
import cc.flyflow.pricelab.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private SpiderService spiderService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> compareProducts(String keyword) {
        // 1. 爬虫搜索商品
        List<Product> products = spiderService.searchProducts(keyword);
        
        // 2. 保存或更新商品信息
        for (Product product : products) {
            Product existingProduct = productRepository.findByProductNameAndPlatform(product.getProductName(), product.getPlatform());
            if (existingProduct != null) {
                // 更新价格和其他信息
                existingProduct.setPrice(product.getPrice());
                existingProduct.setShopName(product.getShopName());
                existingProduct.setDiscount(product.getDiscount());
                productRepository.save(existingProduct);
            } else {
                // 保存新商品
                productRepository.save(product);
            }
        }
        
        return products;
    }
    
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
