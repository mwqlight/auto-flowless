package cc.flyflow.biz.vo;

import cc.flyflow.biz.entity.ProcessInstanceRecord;
import cn.hutool.core.lang.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-07-28 15:23
 */
@ApiModel(description = "")
@Data
public class ProcessInstanceRecordVO extends ProcessInstanceRecord {

    /**
     *  表单值显示
     */
    @ApiModelProperty("表单值显示")
    private List<Dict> formValueShowList;

    /**
     * 发起人名字
     */
    @ApiModelProperty("发起人名字")
    private String rootUserName;

    public String getRootUserName() {
        return rootUserName;
    }

    public void setRootUserName(String rootUserName) {
        this.rootUserName = rootUserName;
    }

    /**
     * 发起人头像
     */
    @ApiModelProperty("发起人头像")
    private String rootUserAvatarUrl;

    public String getRootUserAvatarUrl() {
        return rootUserAvatarUrl;
    }

    public void setRootUserAvatarUrl(String rootUserAvatarUrl) {
        this.rootUserAvatarUrl = rootUserAvatarUrl;
    }

    /**
     * 是否允许撤销
     */
    @ApiModelProperty("是否允许撤销")
    private Boolean cancelEnable;

    public Boolean getCancelEnable() {
        return cancelEnable;
    }

    public void setCancelEnable(Boolean cancelEnable) {
        this.cancelEnable = cancelEnable;
    }

    /**
     * 正在处理的人员显示
     */
    @ApiModelProperty("正在处理的人员显示")
    private String taskAssignShow;

    public String getTaskAssignShow() {
        return taskAssignShow;
    }

    public void setTaskAssignShow(String taskAssignShow) {
        this.taskAssignShow = taskAssignShow;
    }

    /**
     * 发起时间显示
     */
    @ApiModelProperty("发起时间显示")
    private String startTimeShow;

    public String getStartTimeShow() {
        return startTimeShow;
    }

    public void setStartTimeShow(String startTimeShow) {
        this.startTimeShow = startTimeShow;
    }

    public List<Dict> getFormValueShowList() {
        return formValueShowList;
    }

    public void setFormValueShowList(List<Dict> formValueShowList) {
        this.formValueShowList = formValueShowList;
    }

    /**
     * 流程唯一id
     */
    @ApiModelProperty("流程唯一id")
    private String flowUniqueId;

    public String getFlowUniqueId() {
        return flowUniqueId;
    }

    public void setFlowUniqueId(String flowUniqueId) {
        this.flowUniqueId = flowUniqueId;
    }

    public void setFormData(String formData) {
        // ProcessInstanceRecordVO类继承自ProcessInstanceRecord，所以应该调用super.setFormData()
        super.setFormData(formData);
    }

    // 注意：ProcessInstanceRecord类中没有process字段，所以需要在ProcessInstanceRecordVO中添加process字段和对应的getter/setter方法
    private String process;

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
