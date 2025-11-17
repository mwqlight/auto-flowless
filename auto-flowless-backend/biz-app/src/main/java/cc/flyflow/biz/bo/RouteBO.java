package cc.flyflow.biz.bo;

import cc.flyflow.biz.constants.MenuTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 路由
 */
@Data
public class RouteBO {
    /**
     *
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    private MenuTypeEnum type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 权限标识
     */
    private String perm;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    private Boolean visible;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色列表
     */
    private List<String> roles;

    /**
     * 获取菜单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 获取角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色列表
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 跳转路径
     */
    private String redirect;

    /**
     * 拥有路由的权限
     */
    private List<String> roles;

    private String roleId;
}
