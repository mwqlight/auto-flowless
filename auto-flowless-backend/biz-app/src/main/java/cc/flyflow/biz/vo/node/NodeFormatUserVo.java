package cc.flyflow.biz.vo.node;

import cc.flyflow.biz.constants.NodeFormatUserVoStatusEnum;
import cc.flyflow.common.constants.TaskTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 节点用户
 */
@ApiModel(description = "节点用户")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeFormatUserVo {

    /**
     * 用户od
     */
    @ApiModelProperty(value = "用户od", position = 1)
    private String id;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", position = 2)
    private String name;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", position = 3)
    private Date showTime;
    /**
     * 操作时间格式化字符串显示
     */
    @ApiModelProperty(value = "操作时间格式化字符串显示", position = 4)
    private String showTimeStr;
    /**
     * 签署合同的url
     */
    @ApiModelProperty(value = "签署合同的url", position = 5)
    private String signUrl;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", position = 6)
    private String avatar;
    /**
     * 签署合同结果
     */
    @ApiModelProperty(value = "签署合同结果", position = 7)
    private Boolean signContractResult;

    /**
     * 操作类型 {@link TaskTypeEnum}
     */
    @ApiModelProperty(value = "操作类型 {@link constants.cc.flyflow.common.TaskTypeEnum}", position = 8)
    private String operType;
    /**
     * 状态 1进行中2已完成 {@link NodeFormatUserVoStatusEnum}
     */
    @ApiModelProperty(value = "状态 1进行中2已完成 {@link constants.cc.flyflow.biz.NodeFormatUserVoStatusEnum}", position = 9)
    private Integer status = 0;

    // 静态内部类Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private NodeFormatUserVo vo = new NodeFormatUserVo();

        public Builder id(String id) {
            vo.id = id;
            return this;
        }

        public Builder name(String name) {
            vo.name = name;
            return this;
        }

        public Builder avatar(String avatar) {
            vo.avatar = avatar;
            return this;
        }

        public Builder status(Integer status) {
            vo.status = status;
            return this;
        }

        public Builder showTime(Date showTime) {
            vo.showTime = showTime;
            return this;
        }

        public Builder showTimeStr(String showTimeStr) {
            vo.showTimeStr = showTimeStr;
            return this;
        }

        public Builder operType(String operType) {
            vo.operType = operType;
            return this;
        }

        public Builder signUrl(String signUrl) {
            vo.signUrl = signUrl;
            return this;
        }

        public Builder signContractResult(Boolean signContractResult) {
            vo.signContractResult = signContractResult;
            return this;
        }

        public NodeFormatUserVo build() {
            return vo;
        }
    }

    // 手动添加setter方法以确保编译通过
    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public void setShowTimeStr(String showTimeStr) {
        this.showTimeStr = showTimeStr;
    }

    public Date getShowTime() {
        return showTime;
    }

    public String getShowTimeStr() {
        return showTimeStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getId() {
        return id;
    }
}
