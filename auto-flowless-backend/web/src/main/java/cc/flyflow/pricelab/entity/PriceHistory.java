package cc.flyflow.pricelab.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "price_lab_price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 商品ID
    private Long productId;
    
    // 价格
    private BigDecimal price;
    
    // 记录时间
    private Date recordTime;
    
    // 平台名称
    private String platform;
    
    @PrePersist
    protected void onCreate() {
        recordTime = new Date();
    }
}