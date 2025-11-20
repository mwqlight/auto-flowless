package cc.flyflow.pricelab.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "price_lab_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 商品名称
    private String productName;
    
    // 商品URL
    private String productUrl;
    
    // 平台名称
    private String platform;
    
    // 价格
    private BigDecimal price;
    
    // 店铺名称
    private String shopName;
    
    // 优惠信息
    private String discount;
    
    // 商品图片
    private String imageUrl;
    
    // 创建时间
    @Column(updatable = false)
    private Date createTime;
    
    // 更新时间
    private Date updateTime;
    
    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        updateTime = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}