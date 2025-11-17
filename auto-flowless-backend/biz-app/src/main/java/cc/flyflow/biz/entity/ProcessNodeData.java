package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 流程节点数据
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProcessNodeData  extends BaseEntity {


    /**
     * 流程id
     */
    @TableField("`flow_id`")
    private String flowId;

    /**
     * 表单数据
     */
    @TableField("`data`")
    private String data;
    @TableField("`node_id`")
    private String nodeId;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 设置租户ID
     */
    public void setTenantId(String tenantId) {
        super.setTenantId(tenantId);
    }


}
