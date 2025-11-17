package cc.flyflow.biz.vo;

import cc.flyflow.biz.constants.MenuTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Long id;

    private Long parentId;

    private String name;

    private MenuTypeEnum type;

    private String path;

    private String component;

    private Integer sort;

    private Integer visible;

    private String icon;

    private String redirect;

    private String perm;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuVO> children;

    /**
     * 获取权限标识
     */
    public String getPerm() {
        return perm;
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
     * 获取菜单类型
     */
    public MenuTypeEnum getType() {
        return type;
    }

    /**
     * 获取主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 获取父节点ID
     */
    public Long getParentId() {
        return parentId;
    }

}
