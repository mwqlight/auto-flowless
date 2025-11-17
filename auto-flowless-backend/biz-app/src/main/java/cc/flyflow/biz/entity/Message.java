package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 通知消息
 * </p>
 *
 * @author Vincent
 * @since 2023-07-25
 */
@Getter
@Setter
@Accessors(chain = true)
public class Message  extends BaseEntity {

    // 手动添加缺失的getter方法
    public Boolean getReaded() {
        return readed;
    }

    public Long getTenantId() {
        // BaseEntity中应该有tenantId字段
        return super.getTenantId();
    }

    public String getUserId() {
        return userId;
    }

    public Long getId() {
        // BaseEntity中应该有id字段
        return super.getId();
    }

    public Date getCreateTime() {
        // BaseEntity中应该有createTime字段
        return super.getCreateTime();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFlowId() {
        return flowId;
    }


    /**
     * 类型
     */
    @TableField("`type`")
    private String type;

    /**
     * 是否已读
     */
    @TableField("`readed`")
    private Boolean readed;

    /**
     * 用户id
     */
    @TableField("`user_id`")
    private String userId;

    /**
     * 业务唯一id
     */
    @TableField("`biz_unique_id`")
    private String bizUniqueId;

    /**
     * 消息参数
     */
    @TableField("`param`")
    private String param;

    /**
     * 消息内容
     */
    @TableField("`content`")
    private String content;

    /**
     * 消息头
     */
    @TableField("`title`")
    private String title;

    /**
     * 流程id
     */
    @TableField("`flow_id`")
    private String flowId;

    /**
     * 流程实例id
     */
    @TableField("`process_instance_id`")
    private String processInstanceId;



}
