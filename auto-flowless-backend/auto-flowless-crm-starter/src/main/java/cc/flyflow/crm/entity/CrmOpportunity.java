package cc.flyflow.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商机信息实体类
 * @author flyflow
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crm_opportunity")
public class CrmOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商机名称
     */
    private String opportunityName;

    /**
     * 关联线索ID
     */
    private Long clueId;

    /**
     * 关联客户ID
     */
    private Long customerId;

    /**
     * 商机阶段（意向阶段、方案沟通、合同审批、成交）
     */
    private String stage;

    /**
     * 预计金额
     */
    private BigDecimal expectedAmount;

    /**
     * 预计成交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime expectedCloseTime;

    /**
     * 客户经理ID
     */
    private Long managerId;

    /**
     * 客户经理名称
     */
    private String managerName;

    /**
     * 最后跟进时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastFollowTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 删除标记（0：未删除，1：已删除）
     */
    private Integer delFlag;

}
