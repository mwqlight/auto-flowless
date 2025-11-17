package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class Menu  extends BaseEntity {

    /**
     * 父菜单ID
     */
    @TableField("`parent_id`")
    private Long parentId;

    /**
     * 父节点ID路径
     */
    @TableField("`tree_path`")
    private String treePath;

    /**
     * 菜单名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 菜单类型(1:菜单；2:目录；3:外链；4:按钮)
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    @TableField("`path`")
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    @TableField("`component`")
    private String component;

    /**
     * 权限标识
     */
    @TableField("`perm`")
    private String perm;

    /**
     * 显示状态(1-显示;0-隐藏)
     */
    @TableField("`visible`")
    private Boolean visible;

    /**
     * 排序
     */
    @TableField("`sort`")
    private Integer sort;

    /**
     * 菜单图标
     */
    @TableField("`icon`")
    private String icon;

    /**
     * 跳转路径
     */
    @TableField("`redirect`")
    private String redirect;

    /**
     * 获取菜单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置菜单ID
     */
    public Menu setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * 获取菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取路由路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 获取组件路径
     */
    public String getComponent() {
        return component;
    }

    /**
     * 获取菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 获取父菜单ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单ID
     */
    public Menu setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 获取显示状态
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * 获取跳转路径
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * 获取菜单类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 获取排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 获取权限标识
     */
    public String getPerm() {
        return perm;
    }

    /**
     * 设置路由路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 设置组件路径
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 设置父节点ID路径
     */
    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    /**
     * 获取父节点ID路径
     */
    public String getTreePath() {
        return treePath;
    }

    /**
     * 设置菜单类型
     */
    public void setType(MenuTypeEnum type) {
        this.type = type;
    }
}
