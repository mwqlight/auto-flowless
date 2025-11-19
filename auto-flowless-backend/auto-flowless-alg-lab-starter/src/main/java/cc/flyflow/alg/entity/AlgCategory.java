package cc.flyflow.alg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cc.flyflow.biz.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 算法分类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_alg_category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlgCategory extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 分类名称
     */
    private String categoryName;
    
    /**
     * 分类代码
     */
    private String categoryCode;
    
    /**
     * 父分类ID
     */
    private Long parentId;
    
    /**
     * 分类层级
     */
    private Integer level;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 状态 0:禁用 1:启用
     */
    private Integer status;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 子分类列表
     */
    private List<AlgCategory> children;
}
