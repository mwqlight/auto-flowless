package cc.flyflow.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户信息实体类
 * @author flyflow
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crm_customer")
public class CrmCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 统一社会信用代码
     */
    private String creditCode;

    /**
     * 行业类型
     */
    private String industryType;

    /**
     * 企业规模
     */
    private String enterpriseScale;

    /**
     * 控股股东
     */
    private String controllingShareholder;

    /**
     * 客户等级（A/B/C类）
     */
    private String customerLevel;

    /**
     * 客户标签（JSON格式存储）
     */
    private List<String> tags;

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
     * 跟进状态
     */
    private String followStatus;

    /**
     * 是否在公海池（0：否，1：是）
     */
    private Integer isPublicPool;

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
     * 删除标记（0：正常，1：删除）
     */
    private Integer delFlag;

}