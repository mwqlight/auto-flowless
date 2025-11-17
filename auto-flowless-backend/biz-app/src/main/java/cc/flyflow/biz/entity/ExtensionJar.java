package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 扩展JAR包实体类
 * @author Vincent
 * @since 2023-12-01
 */
@Data
@ApiModel(description = "扩展JAR包实体类")
@TableName("extension_jar")
public class ExtensionJar extends BaseEntity {

    @ApiModelProperty(value = "JAR包名称")
    private String jarName;

    @ApiModelProperty(value = "JAR包标识")
    private String jarKey;

    @ApiModelProperty(value = "JAR包类型")
    private String jarType;

    @ApiModelProperty(value = "JAR包版本")
    private String version;

    @ApiModelProperty(value = "JAR包描述")
    private String description;

    @ApiModelProperty(value = "JAR包路径")
    private String jarPath;

    @ApiModelProperty(value = "JAR包大小")
    private Long jarSize;

    @ApiModelProperty(value = "状态：0-禁用，1-启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 获取状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
