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

/**
 * 线索信息实体类
 * @author flyflow
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crm_clue")
public class CrmClue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 线索名称
     */
    private String clueName;

    /**
     * 线索来源（如公开数据抓取、银企沙龙活动录入）
     */
    private String source;

    /**
     * 线索状态（未联系、已接触、转化中、已成交）
     */
    private String status;

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
