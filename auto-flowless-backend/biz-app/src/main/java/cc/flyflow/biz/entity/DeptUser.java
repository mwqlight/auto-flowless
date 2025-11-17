package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门-用户表
 *
 * @author Vincent
 * @since 2023-12-27
 */
@Getter
@Setter
@Accessors(chain = true)
public class DeptUser extends BaseEntity {


    /**
     * 部门id
     */
    @TableField("`dept_id`")
    private String  deptId;


    /**
     * 主管user_id
     */
    @TableField("`user_id`")
    private String userId;

    // 手动添加setter方法以确保编译通过
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // 手动添加getter方法以确保编译通过
    public String getDeptId() {
        return deptId;
    }

    public String getUserId() {
        return userId;
    }
}
