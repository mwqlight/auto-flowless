package cc.flyflow.biz.entity;

import cc.flyflow.biz.constants.ValidGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 流程主表
 */
@ApiModel(description = "流程主表")
@Getter
@Setter
@Accessors(chain = true)
public class ProcessMain extends BaseEntity {

    /**
     * 唯一性ID
     */
    @TableField("`unique_id`")
    private String uniqueId;

    /**
     * 表单名称
     */
    @ApiModelProperty(value = "表单名称", position = 2, required = true)
    @NotBlank(groups = {ValidGroup.Crud.Create.class},message = "表单名称不能为空")
    @TableField("`name`")
    private String name;

    /**
     * 获取唯一性ID
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置唯一性ID
     */
    public ProcessMain setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    /**
     * 获取租户ID
     */
    public String getTenantId() {
        return super.getTenantId();
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    /**
     * 图标配置
     */
    @ApiModelProperty(value = "图标配置", position = 3, required = true)
    @NotBlank(groups = {ValidGroup.Crud.Create.class},message = "表单头像不能为空")
    @TableField("`logo`")
    private String logo;



    /**
     * 分组ID
     */
    @ApiModelProperty(value = "分组ID", position = 5, required = true)
    @NotNull(groups = {ValidGroup.Crud.Create.class},message = "请设置分组")
    @TableField("`group_id`")
    private Long groupId;

    /**
     * 获取分组ID
     */
    public Long getGroupId() {
        return groupId;
    }




    @ApiModelProperty(hidden = true)
    @TableField("`sort`")
    private Integer sort;

    /**
     * 获取排序值
     */
    public Integer getSort() {
        return sort;
    }



    /**
     * 范围描述显示
     */
    @ApiModelProperty(value = "范围描述显示", position = 15)
    @TableField("`range_show`")
    private String rangeShow;

    public String getRangeShow() {
        return rangeShow;
    }

    public void setRangeShow(String rangeShow) {
        this.rangeShow = rangeShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 唯一性id
     */
    @ApiModelProperty(value = "唯一性id", position = 16)
    @TableField("`unique_id`")
    private String uniqueId;




}
