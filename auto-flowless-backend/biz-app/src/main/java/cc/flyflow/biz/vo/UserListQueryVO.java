package cc.flyflow.biz.vo;

import cc.flyflow.common.dto.PageDto;
import lombok.Data;

import java.util.List;

@Data
public class UserListQueryVO extends PageDto {
    /**
     * 用户状态 1在职 2离职
     */
    private Integer status;

    private String name;
    private String keywords;
    /**
     * 部门ID列表
     */
    private List<String> deptIdList;

    /**
     * 获取状态
     *
     * @return 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取关键字
     *
     * @return 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 获取部门ID列表
     *
     * @return 部门ID列表
     */
    public List<String> getDeptIdList() {
        return deptIdList;
    }

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 获取部门id
     *
     * @return 部门id
     */
    public String getDeptId() {
        return deptId;
    }

}
