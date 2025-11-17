package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 流程节点记录-执行人
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProcessInstanceAssignUserRecord extends BaseEntity {

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    /**
     * 流程id
     */
    @TableField("`flow_id`")
    private String flowId;

    public String getTenantId() {
        return tenantId;
    }

    /**
     * 流程实例id
     */
    @TableField("`process_instance_id`")
    private String processInstanceId;
    /**
     * 节点执行id
     */
    @ApiModelProperty("节点执行id")
    private String parentExecutionId;
    /**
     * 表单数据
     */
    @TableField("`data`")
    private String data;
    @TableField("`node_id`")
    private String nodeId;

    // 手动添加getter方法以确保编译通过
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocalData() {
        return localData;
    }

    public void setLocalData(String localData) {
        this.localData = localData;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    public String getParentExecutionId() {
        return parentExecutionId;
    }

    public void setParentExecutionId(String parentExecutionId) {
        this.parentExecutionId = parentExecutionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     *  用户id
     */
    @TableField("`user_id`")
    private String userId;

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
     *  任务id
     */
    @TableField("`task_id`")
    private String taskId;

    /**
     * 审批意见
     */
    @TableField("`approve_desc`")
    private String approveDesc;

    /**
     *  节点名称
     */
    @TableField("`node_name`")
    private String nodeName;

    /**
     * 任务类型
     */
    @TableField("`task_type`")
    private String taskType;

    /**
     * 表单本地数据
     */
    @TableField("`local_data`")
    private String localData;

    /**
     * 是否是自动完成
     */
    @TableField("`auto`")
    private Boolean auto;


}
