package cc.flyflow.biz.vo;

import cc.flyflow.biz.constants.ProcessInstanceRecordStatusEnum;
import cc.flyflow.common.dto.PageDto;
import cc.flyflow.common.dto.flow.NodeUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 流程数据参数对象
 */
@ApiModel(description = "流程数据参数对象")
@Data
public class ProcessDataQueryVO extends PageDto {


    /**
     * 流程id
     */
    @ApiModelProperty("流程id")
    private String flowId;
    /**
     * 流程编码
     */
    @ApiModelProperty("流程编码")
    private String processBizCode;

    public String getProcessBizCode() {
        return processBizCode;
    }

    public void setProcessBizCode(String processBizCode) {
        this.processBizCode = processBizCode;
    }
    /**
     * 流程id集合
     */
    @ApiModelProperty("流程id集合")
    private List<String> flowIdList;
    /**
     * 流程结束时间 开始-结束
     */
    @ApiModelProperty("流程结束时间 开始-结束")
    private List<String> finishTime;
    /**
     * 流程发起时间  开始结束
     */
    @ApiModelProperty("流程发起时间  开始结束")
    private List<String> startTime;
    /**
     * 发起人集合
     */
    @ApiModelProperty("发起人集合")
    private List<NodeUser> starterList;
    /**
     * 流程状态 {@link ProcessInstanceRecordStatusEnum}
     */
    @ApiModelProperty("流程状态 {@link cc.flyflow.biz.constants.ProcessInstanceRecordStatusEnum}")
    private Integer status;

    // Add missing getter and setter methods
    public List<String> getFlowIdList() {
        return flowIdList;
    }

    public void setFlowIdList(List<String> flowIdList) {
        this.flowIdList = flowIdList;
    }

    public List<NodeUser> getStarterList() {
        return starterList;
    }

    public void setStarterList(List<NodeUser> starterList) {
        this.starterList = starterList;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public void setStartTime(List<String> startTime) {
        this.startTime = startTime;
    }

    public List<String> getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(List<String> finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
