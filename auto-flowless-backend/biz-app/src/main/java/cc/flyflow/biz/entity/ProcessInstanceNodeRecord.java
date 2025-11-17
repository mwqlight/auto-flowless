package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 流程节点记录
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Data
@Accessors(chain = true)
public class ProcessInstanceNodeRecord extends BaseEntity {


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

    /**
     * 表单数据
     */
    @TableField("`data`")
    private String data;
    @TableField("`node_id`")
    private String nodeId;
    /**
     * 流程唯一标识
     */
    @TableField("`flow_unique_id`")
    private String flowUniqueId;

    /**
     * 节点类型
     */
    @TableField("`node_type`")
    private Integer nodeType;

    /**
     * 节点名字
     */
    @TableField("`node_name`")
    private String nodeName;

    /**
     * 节点状态
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 开始时间
     */
    @TableField("`start_time`")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("`end_time`")
    private Date endTime;

    /**
     * 执行id
     */
    @TableField("`execution_id`")
    private String executionId;

    /**
     * 上一层级id
     */
    @TableField("`parent_node_id`")
    private String parentNodeId;

    // 手动添加getter方法以确保编译通过
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    // 从BaseEntity继承的方法
    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }
}
