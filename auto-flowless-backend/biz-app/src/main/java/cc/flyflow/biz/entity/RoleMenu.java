package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleMenu  extends BaseEntity {

    /**
     * 角色ID
     */
    @TableField("`role_id`")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField("`menu_id`")
    private Long menuId;

    /**
     * 获取角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 获取菜单ID
     */
    public Long getMenuId() {
        return menuId;
    }

}
