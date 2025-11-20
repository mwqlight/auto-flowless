package cc.flyflow.pricelab.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "price_lab_price_monitor")
public class PriceMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 商品ID
    private Long productId;
    
    // 用户ID
    private Long userId;
    
    // 目标价格
    private BigDecimal targetPrice;
    
    // 监控状态：0-未激活，1-激活
    private Integer status;
    
    // 创建时间
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