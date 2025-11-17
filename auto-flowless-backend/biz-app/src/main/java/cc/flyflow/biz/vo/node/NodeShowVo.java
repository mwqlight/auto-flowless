package cc.flyflow.biz.vo.node;

import cc.flyflow.biz.constants.ProcessInstanceNodeRecordStatusEnum;
import cc.flyflow.biz.vo.ProcessFormatNodeApproveDescVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 流程节点显示对象
 */
@ApiModel(description = "流程节点显示对象")
@Data
public class NodeShowVo {
    /**
     * 节点id
     */
    @ApiModelProperty("节点id")
    private String id;

    /**
     * 用户列表
     */
    @ApiModelProperty("用户列表")
    private List<NodeFormatUserVo> userVoList;

    /**
     * 显示
     */
    @ApiModelProperty("显示")
    private String placeholder;
    /**
     * 状态 1进行中2已完成3已撤销0未开始 {@link ProcessInstanceNodeRecordStatusEnum}
     */
    @ApiModelProperty("状态 1进行中2已完成3已撤销0未开始 {@link ProcessInstanceNodeRecordStatusEnum}")
    private Integer status;

    /**
     * 设置状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取节点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取节点类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置节点类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 节点名称
     */
    @ApiModelProperty("节点名称")
    private String name;
    /**
     * 节点类型
     */
    @ApiModelProperty("节点类型")
    private Integer type;
    /**
     * 发起人选择用户
     */
    @ApiModelProperty("发起人选择用户")
    private Boolean selectUser;


    /**
     * 是否多选
     */
    @ApiModelProperty("是否多选")
    private Boolean multiple;

    /**
     * 子级列表
     */
    @ApiModelProperty("子级列表")
    private List<NodeShowVo> children;

    // 手动添加setter方法以确保编译通过
    public void setSelectUser(Boolean selectUser) {
        this.selectUser = selectUser;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }
    /**
     * 分支列表
     */
    @ApiModelProperty("分支列表")
    private List<NodeShowVo> branch;
    /**
     * 显示时间
     */
    @ApiModelProperty("显示时间")
    private String showTimeStr;

    /**
     * 评论列表
     */
    @ApiModelProperty("评论列表")
    private List<ProcessFormatNodeApproveDescVo> approveDescList;
    /**
     * 子流程列表
     */
    @ApiModelProperty("子流程列表")
    private List<SubProcessShowVo> subProcessShowVoList;

    /**
     * 子流程显示对象
     */
    @ApiModel(description = "子流程显示对象")
    @Data
    public static class SubProcessShowVo {
        /**
         * 流程实例id
         */
        @ApiModelProperty("流程实例id")
        private String processInstanceId;
        /**
         * 发起人id
         */
        @ApiModelProperty("发起人id")
        private String startUserId;
        /**
         * 发起人姓名
         */
        @ApiModelProperty("发起人姓名")
        private String startUserName;
        /**
         * 流程名称
         */
        @ApiModelProperty("流程名称")
        private String processName;
        /**
         * 流程状态
         */
        @ApiModelProperty("流程状态")
        private Integer processStatus;
    }

    // 手动添加setter方法以确保编译通过
    public void setApproveDescList(List<ProcessFormatNodeApproveDescVo> approveDescList) {
        this.approveDescList = approveDescList;
    }

    public void setShowTimeStr(String showTimeStr) {
        this.showTimeStr = showTimeStr;
    }

    public void setUserVoList(List<NodeFormatUserVo> userVoList) {
        this.userVoList = userVoList;
    }

    public void setChildren(List<NodeShowVo> children) {
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBranch(List<NodeShowVo> branch) {
        this.branch = branch;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
