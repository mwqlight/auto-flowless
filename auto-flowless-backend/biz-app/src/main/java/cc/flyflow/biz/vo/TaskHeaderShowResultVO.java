package cc.flyflow.biz.vo;

import cc.flyflow.common.constants.ApproveResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 表单头部显示对象
 * @author Huijun Zhao
 * @description
 * @date 2023-10-12 09:47
 */
@ApiModel(description = "表单头部显示对象")
@Data
public class TaskHeaderShowResultVO {

    /**
     * 流程实例id
     */
    @ApiModelProperty(value = "流程实例id", position = 1)
    private String processInstanceId;
    /**
     * 发起人姓名
     */
    @ApiModelProperty(value = "发起人姓名", position = 2)
    private String starterName;
    /**
     * 发起人头像
     */
    @ApiModelProperty(value = "发起人头像", position = 3)
    private String starterAvatarUrl;
    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称", position = 4)
    private String processName;
    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间", position = 5)
    private Date startTime;
    /**
     * 列出结果 {@link  ApproveResultEnum}
     */
    @ApiModelProperty(value = "列出结果 {@link  ApproveResultEnum}", position = 6)
    private Integer processInstanceResult;
    /**
     * 流程业务key
     */
    @ApiModelProperty(value = "流程业务key", position = 7)
    private String processInstanceBizKey;
    /**
     * 流程业务编码
     */
    @ApiModelProperty(value = "流程业务编码", position = 8)
    private String processInstanceBizCode;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getStarterName() {
        return starterName;
    }

    public void setStarterName(String starterName) {
        this.starterName = starterName;
    }

    public String getStarterAvatarUrl() {
        return starterAvatarUrl;
    }

    public void setStarterAvatarUrl(String starterAvatarUrl) {
        this.starterAvatarUrl = starterAvatarUrl;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getProcessInstanceResult() {
        return processInstanceResult;
    }

    public void setProcessInstanceResult(Integer processInstanceResult) {
        this.processInstanceResult = processInstanceResult;
    }

    public String getProcessInstanceBizKey() {
        return processInstanceBizKey;
    }

    public void setProcessInstanceBizKey(String processInstanceBizKey) {
        this.processInstanceBizKey = processInstanceBizKey;
    }

    public String getProcessInstanceBizCode() {
        return processInstanceBizCode;
    }

    public void setProcessInstanceBizCode(String processInstanceBizCode) {
        this.processInstanceBizCode = processInstanceBizCode;
    }

}
