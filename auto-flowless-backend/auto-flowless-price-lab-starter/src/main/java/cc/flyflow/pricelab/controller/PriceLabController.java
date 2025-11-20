package cc.flyflow.pricelab.controller;

import cc.flyflow.pricelab.entity.Product;
import cc.flyflow.pricelab.service.ProductService;
import cc.flyflow.pricelab.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-lab")
public class PriceLabController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private SpiderService spiderService;
    
    /**
     * 根据关键词搜索商品并比价
     * @param keyword 搜索关键词
     * @return 商品列表
     */
    @GetMapping("/compare")
    public List<Product> compareProducts(@RequestParam String keyword) {
        return productService.compareProducts(keyword);
    }
    
    /**
     * 获取商品价格历史
     * @param productId 商品ID
     * @return 价格历史
     */
    @GetMapping("/price-history/{productId}")
    public Object getPriceHistory(@PathVariable Long productId) {
        // 这里需要实现价格历史查询
        return null;
    }
    
    /**
     * 添加商品到价格监控
     * @param monitor 监控信息
     * @return 监控结果
     */
    @PostMapping("/monitor")
    public Object addMonitor(@RequestBody Object monitor) {
        // 这里需要实现价格监控添加
        return null;
    }
    
    /**
     * 获取商品信息
     * @param productId 商品ID
     * @return 商品信息
     */
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
