package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 流程发起人
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProcessStarter  extends BaseEntity {

    /**
     * 租户id
     */
    @TableField("`tenant_id`")
    private String tenantId;

    /**
     * 用户id或者部门id
     */
    @TableField("`type_id`")
    private String typeId;

    /**
     *  类型 user dept
     */
    @TableField("`type`")
    private String type;

    /**
     * 流程表id
     */
    @TableField("`process_id`")
    private String processId;

    /**
     * 流程id
     */
    @TableField("`flow_id`")
    private String flowId;

    /**
     * 数据
     */
    @TableField("`data`")
    private String data;



    /**
     * 获取流程表id
     */
    public String getProcessId() {
        return processId;
    }





    /**
     * 设置流程表id
     */
    public ProcessStarter setProcessId(String processId) {
        this.processId = processId;
        return this;
    }

    /**
     * 获取数据
     */
    public String getData() {
        return data;
    }

    /**
     * 设置数据
     */
    public ProcessStarter setData(String data) {
        this.data = data;
        return this;
    }

    /**
     * 是否包含下级部门
     */
    @TableField("contain_children_dept")
    private Boolean containChildrenDept;



    /**
     * 设置是否包含下级部门
     */
    public void setContainChildrenDept(Boolean containChildrenDept) {
        this.containChildrenDept = containChildrenDept;
    }

    /**
     * 获取是否包含下级部门
     */
    public Boolean getContainChildrenDept() {
        return containChildrenDept;
    }

    /**
     * 设置流程id
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 设置类型 user dept
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 设置用户id或者部门id
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取用户id或者部门id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 获取流程id
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * 获取租户ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 设置类型 user dept
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取类型 user dept
     */
    public String getType() {
        return type;
    }
}
