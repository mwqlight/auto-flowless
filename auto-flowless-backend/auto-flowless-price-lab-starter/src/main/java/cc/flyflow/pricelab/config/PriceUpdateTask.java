package cc.flyflow.pricelab.config;

import cc.flyflow.pricelab.entity.Product;
import cc.flyflow.pricelab.repository.ProductRepository;
import cc.flyflow.pricelab.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PriceUpdateTask {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SpiderService spiderService;
    
    /**
     * 每天凌晨2点更新所有商品价格
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateAllProductPrices() {
        log.info("开始更新所有商品价格...");
        
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            try {
                spiderService.updateProductPrice(product.getId());
                log.info("更新商品价格成功: {}", product.getProductName());
            } catch (Exception e) {
                log.error("更新商品价格失败: {}", product.getProductName(), e);
            }
        }
        
        log.info("所有商品价格更新完成");
    }
    
    /**
     * 每小时更新热门商品价格
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void updateHotProductPrices() {
        log.info("开始更新热门商品价格...");
        // 这里可以实现热门商品的判断逻辑，例如根据销量或浏览量
        // 暂时只更新前10个商品
        List<Product> products = productRepository.findAll();
        if (products.size() > 10) {
            products = products.subList(0, 10);
        }
        
        for (Product product : products) {
            try {
                spiderService.updateProductPrice(product.getId());
                log.info("更新热门商品价格成功: {}", product.getProductName());
            } catch (Exception e) {
                log.error("更新热门商品价格失败: {}", product.getProductName(), e);
            }
        }
        
        log.info("热门商品价格更新完成");
    }
}
