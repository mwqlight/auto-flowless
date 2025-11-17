package cc.flyflow.biz.vo;

import cc.flyflow.common.dto.flow.Node;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 操作类型对象
 * @author Huijun Zhao
 * @description
 * @date 2023-10-12 09:47
 */
@ApiModel(description = "操作类型对象")
@Data
public class TaskOperDataResultVO {

    /**
     * 流程实例id
     */
    @ApiModelProperty(value = "流程实例id", position = 1)
    private String processInstanceId;
    /**
     * 节点id
     */
    @ApiModelProperty(value = "节点id", position = 2)
    private String nodeId;
    /**
     * 任务是否存在
     */
    @ApiModelProperty(value = "任务是否存在", position = 3)
    private Boolean taskExist;
    /**
     * 是否是前加签
     */
    @ApiModelProperty(value = "是否是前加签", position = 4)
    private Boolean frontJoinTask;
    /**
     * 操作列表
     */
    @ApiModelProperty(value = "操作列表", position = 5)
    private List operList;
    /**
     * 当前节点
     */
    @ApiModelProperty(value = "当前节点", position = 6)
    private Node node;
    /**
     * 原始流程数据
     */
    @ApiModelProperty(value = "原始流程数据", position = 7)
    private Node process;
    /**
     * 是否需求签字
     */
    @ApiModelProperty(value = "是否需求签字", position = 8)
    private Boolean needSignature;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Boolean getTaskExist() {
        return taskExist;
    }

    public void setTaskExist(Boolean taskExist) {
        this.taskExist = taskExist;
    }

    public Boolean getFrontJoinTask() {
        return frontJoinTask;
    }

    public void setFrontJoinTask(Boolean frontJoinTask) {
        this.frontJoinTask = frontJoinTask;
    }

    public List getOperList() {
        return operList;
    }

    public void setOperList(List operList) {
        this.operList = operList;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getProcess() {
        return process;
    }

    public void setProcess(Node process) {
        this.process = process;
    }

    public Boolean getNeedSignature() {
        return needSignature;
    }

    public void setNeedSignature(Boolean needSignature) {
        this.needSignature = needSignature;
    }

}
