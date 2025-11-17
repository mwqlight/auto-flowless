package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Vincent
 * @since 2023-07-06
 */
@Getter
@Setter
@Accessors(chain = true)
public class User  extends BaseEntity {



    /**
     * 用户名
     */
    @TableField("`name`")
    private String name;

    /**
     * 拼音  全拼
     */
    @TableField("`pinyin`")
    private String pinyin;

    /**
     * 直属领导id
     */
    @TableField("`parent_id`")
    private Long parentId;

    /**
     * 拼音, 首字母缩写
     */
    @TableField("`py`")
    private String py;



    /**
     * 头像url
     */
    @TableField("`avatar_url`")
    private String avatarUrl;



    /**
     * 登录密码
     */
    @TableField(value = "`password`",select = false)
    private String password;

    /**
     * 手机号
     */
    @TableField("`phone`")
    private String phone;
    @TableField("`status`")
    private Integer status;

    // 手动添加getter方法以确保编译通过
    public String getPassword() {
        return password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        // User类继承自BaseEntity，所以应该调用super.getId()
        return super.getId();
    }

    public Integer getStatus() {
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }

}
