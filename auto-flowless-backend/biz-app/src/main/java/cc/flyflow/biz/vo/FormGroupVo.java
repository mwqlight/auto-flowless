package cc.flyflow.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 流程组
 */
@ApiModel(description = "流程组")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormGroupVo {
    /**
     * id
     */
    @ApiModelProperty("id")
    private String id;
    /**
     * 名字
     */
    @ApiModelProperty("名字")
    private String name;

    /**
     * 流程
     */
    @ApiModelProperty("流程")
    private List<FlowVo> items;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<FlowVo> items) {
        this.items = items;
    }

    public List<FlowVo> getItems() {
        return items;
    }

    /**
     * 流程对象
     */
    @ApiModel(description = "流程对象")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FlowVo {
        /**
         * 流程id
         */
        @ApiModelProperty("流程id")
        private String flowId;

        /**
         * 发起范围
         */
        @ApiModelProperty("发起范围")
        private String rangeShow;
        /**
         * 名字
         */
        @ApiModelProperty("名字")
        private String name;
        /**
         * 图标
         */
        @ApiModelProperty("图标")
        private String logo;
        /**
         * 是否已停止
         */
        @ApiModelProperty("是否已停止")
        private Boolean stop;
        /**
         * 备注
         */
        @ApiModelProperty("备注")
        private String remark;
        /**
         * 更新日期
         */
        @ApiModelProperty("更新日期")
        private Date updated;
        /**
         * 是否记录报表
         */
        @ApiModelProperty("是否记录报表")
        private Boolean reportEnable;
        /**
         * 流程唯一id
         */
        @ApiModelProperty("流程唯一id")
        private String uniqueId;

        public void setFlowId(String flowId) {
            this.flowId = flowId;
        }

        public void setRangeShow(String rangeShow) {
            this.rangeShow = rangeShow;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public void setStop(Boolean stop) {
            this.stop = stop;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setUpdated(Date updated) {
            this.updated = updated;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public void setReportEnable(Boolean reportEnable) {
            this.reportEnable = reportEnable;
        }
    }


}
