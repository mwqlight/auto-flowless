package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 流程记录
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@ApiModel(description = "<p> 流程记录 </p>")
@Getter
    @Setter
    @Accessors(chain = true)
    public class ProcessInstanceRecord  extends BaseEntity {
        public String getProcessInstanceBizKey() {
            return processInstanceBizKey;
        }



    /**
     * 流程名字
     */
    @ApiModelProperty("流程名字")
    @TableField("`name`")
    private String name;

    /**
     * 发起人的主部门id
     */
    @ApiModelProperty("发起人的主部门id")
    @TableField("`main_dept_id`")
    private String mainDeptId;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @TableField("`logo`")
    private String logo;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("`user_id`")
    private String userId;


    /**
     * 流程id
     */
    @ApiModelProperty("流程id")
    @TableField("`flow_id`")
    private String flowId;

    /**
     * 流程实例id
     */
    @ApiModelProperty("流程实例id")
    @TableField("`process_instance_id`")
    private String processInstanceId;

    /**
     * 流程实例业务key
     */
    @ApiModelProperty("流程实例业务key")
    @TableField("`process_instance_biz_key`")
    private String processInstanceBizKey;

    /**
     * 流程实例业务编码
     */
    @ApiModelProperty("流程实例业务编码")
    @TableField("`process_instance_biz_code`")
    private String processInstanceBizCode;

    public String getProcessInstanceBizCode() {
        return processInstanceBizCode;
    }

    public void setProcessInstanceBizCode(String processInstanceBizCode) {
        this.processInstanceBizCode = processInstanceBizCode;
    }

    /**
     * 获取发起人的主部门id
     */
    public String getMainDeptId() {
        return mainDeptId;
    }

    /**
     * 设置发起人的主部门id
     */
    public void setMainDeptId(String mainDeptId) {
        this.mainDeptId = mainDeptId;
    }

    /**
     * 表单数据
     */
    @ApiModelProperty("表单数据")
    @TableField("`form_data`")
    private String formData;

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    /**
     * 组id
     */
    @ApiModelProperty("组id")
    @TableField("`group_id`")
    private Long groupId;

    /**
     * 组名称
     */
    // 手动添加getter方法以确保编译通过
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Long getTenantId() {
        // 假设租户ID是BaseEntity的一部分，如果不是需要添加字段
        return super.getTenantId();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return super.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setProcessInstanceBizKey(String processInstanceBizKey) {
        this.processInstanceBizKey = processInstanceBizKey;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcess() {
        return process;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 组名称
     */
    @ApiModelProperty("组名称")
    @TableField("`group_name`")
    private String groupName;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    @TableField("`status`")
    private Integer status;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @TableField("`end_time`")
    private Date endTime;

    /**
     * 上级流程实例id
     */
    @ApiModelProperty("上级流程实例id")
    @TableField("`parent_process_instance_id`")
    private String parentProcessInstanceId;
    /**
     * 流程节点内容
     */
    @ApiModelProperty("流程节点内容")
    @TableField("`process`")
    private String process;
    /**
     * 结果
     */
    @ApiModelProperty("结果")
    @TableField("`result`")
    private Integer result;
    /**
     * 主流程节点执行id
     */
    @TableField("`parent_process_node_execution_id`")
    private String parentProcessNodeExecutionId;



}
