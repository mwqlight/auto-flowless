package cc.flyflow.alg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cc.flyflow.biz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 算法实现实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_alg_implementation")
public class AlgImplementation extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 算法ID
     */
    private Long algId;
    
    /**
     * 实现名称
     */
    private String implName;
    
    /**
     * 实现代码
     */
    private String implCode;
    
    /**
     * 代码内容
     */
    private String codeContent;
    
    /**
     * 动画演示JSON
     */
    private String animationJson;
    
    /**
     * 实现语言
     */
    private String language;
    
    /**
     * 状态 0:禁用 1:启用
     */
    private Integer status;
    
    /**
     * 排序
     */
    private Integer sort;
}
