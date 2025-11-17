package cc.flyflow.biz.vo;

import cc.flyflow.biz.entity.Process;
import cc.flyflow.common.dto.flow.NodeUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 流程对象
 */
@ApiModel(description = "流程对象")
@Data
public class ProcessVO extends Process {
    /**
     * 需要发起人选择的节点id
     */
    @ApiModelProperty("需要发起人选择的节点id")
    private List<String> selectUserNodeId;
    /**
     * 发起人范围
     */
    @ApiModelProperty("发起人范围")
    private List<NodeUser> rangeList;
    /**
     * 变量参数集合
     */
    @ApiModelProperty("变量参数集合")
    private Map<String, Object> variableMap;
    /**
     * 管理员
     */
    @ApiModelProperty("管理员")
    private String admin;

    /**
     * 是否直接发布
     */
    @ApiModelProperty("是否直接发布")
    private Boolean publish=true;

    /**
     * 获取管理员
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * 设置管理员
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * 获取是否直接发布
     */
    public Boolean getPublish() {
        return publish;
    }

    /**
     * 设置是否直接发布
     */
    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    /**
     * 获取主键ID
     */
    public Long getId() {
        return super.getId();
    }

    /**
     * 获取图标配置
     */
    public String getLogo() {
        return super.getLogo();
    }

    /**
     * 获取分组ID
     */
    public Long getGroupId() {
        return super.getGroupId();
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 获取发起人范围
     */
    public List<NodeUser> getRangeList() {
        return rangeList;
    }

    /**
     * 设置发起人范围
     */
    public void setRangeList(List<cc.flyflow.common.dto.flow.NodeUser> rangeList) {
        this.rangeList = rangeList;
    }
}
