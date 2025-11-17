package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户-角色
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserRole  extends BaseEntity {

    /**
     * 用户id
     */
    @TableField("`user_id`")
    private String userId;

    /**
     * 角色id
     */
    @TableField("`role_id`")
    private String roleId;

    // 手动添加setter方法以确保编译通过
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    // 手动添加getter方法以确保编译通过
    public String getUserId() {
        return userId;
    }

    public String getRoleId() {
        return roleId;
    }
}
