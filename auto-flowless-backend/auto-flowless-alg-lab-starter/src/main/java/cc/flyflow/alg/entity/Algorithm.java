package cc.flyflow.alg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cc.flyflow.biz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 算法实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_algorithm")
public class Algorithm extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 算法名称
     */
    private String algName;
    
    /**
     * 算法代码
     */
    private String algCode;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 时间复杂度
     */
    private String timeComplexity;
    
    /**
     * 空间复杂度
     */
    private String spaceComplexity;
    
    /**
     * 算法描述
     */
    private String description;
    
    /**
     * 状态 0:禁用 1:启用
     */
    private Integer status;
    
    /**
     * 排序
     */
    private Integer sort;
}
