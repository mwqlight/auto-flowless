package cc.flyflow.biz.vo;

import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.common.constants.ApproveDescTypeEnum;
import cc.flyflow.common.dto.flow.UploadValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 节点审批描述对象
 * @author Huijun Zhao
 * @description
 * @date 2023-08-10 10:15
 */
@ApiModel(description = "节点审批描述对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessFormatNodeApproveDescVo {
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", position = 1)
    private String desc;
    /**
     * 评论类型 {@link   ApproveDescTypeEnum}
     */
    @ApiModelProperty(value = "评论类型 {@link   ApproveDescTypeEnum}", position = 2)
    private String descType;
    /**
     * 是否是系统评论
     */
    @ApiModelProperty(value = "是否是系统评论", position = 3)
    private Boolean sys;
    /**
     * 评论类型字符串
     */
    @ApiModelProperty(value = "评论类型字符串", position = 4)
    private String descTypeStr;
    /**
     * 显示时间格式化字符串
     */
    @ApiModelProperty(value = "显示时间格式化字符串", position = 5)
    private String showTimeStr;
    /**
     * 用户
     */
    @ApiModelProperty(value = "用户", position = 6)
    private NodeFormatUserVo user;
    /**
     * 日期时间
     */
    @ApiModelProperty(value = "日期时间", position = 7)
    private Date date;

    /**
     * 评论的文件
     */
    @ApiModelProperty(value = "评论的文件", position = 8)
    private List<UploadValue> approveFileList;
    /**
     *  评论的图片
     */
    @ApiModelProperty(value = "评论的图片", position = 9)
    private List<UploadValue> approveImageList;
    /**
     * 签字的url
     */
    @ApiModelProperty(value = "签字的url", position = 10)
    private List<String> signUrlList;

    // 手动添加getter方法以确保编译通过
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String desc;
        private String descType;
        private Boolean sys;
        private String descTypeStr;
        private String showTimeStr;
        private NodeFormatUserVo user;
        private Date date;
        private List<UploadValue> approveFileList;
        private List<UploadValue> approveImageList;
        private List<String> signUrlList;

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder descType(String descType) {
            this.descType = descType;
            return this;
        }

        public Builder sys(Boolean sys) {
            this.sys = sys;
            return this;
        }

        public Builder descTypeStr(String descTypeStr) {
            this.descTypeStr = descTypeStr;
            return this;
        }

        public Builder showTimeStr(String showTimeStr) {
            this.showTimeStr = showTimeStr;
            return this;
        }

        public Builder user(NodeFormatUserVo user) {
            this.user = user;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder approveFileList(List<UploadValue> approveFileList) {
            this.approveFileList = approveFileList;
            return this;
        }

        public Builder approveImageList(List<UploadValue> approveImageList) {
            this.approveImageList = approveImageList;
            return this;
        }

        public Builder signUrlList(List<String> signUrlList) {
            this.signUrlList = signUrlList;
            return this;
        }

        public ProcessFormatNodeApproveDescVo build() {
            return new ProcessFormatNodeApproveDescVo(desc, descType, sys, descTypeStr, showTimeStr, user, date, approveFileList, approveImageList, signUrlList);
        }
    }
}
