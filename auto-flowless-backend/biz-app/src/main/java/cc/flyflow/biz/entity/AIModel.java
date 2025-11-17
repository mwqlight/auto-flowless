package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * AI模型实体类
 * @author Vincent
 * @since 2023-12-01
 */
@Data
@ApiModel(description = "AI模型实体类")
@TableName("ai_model")
public class AIModel extends BaseEntity {

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "模型标识")
    private String modelKey;

    @ApiModelProperty(value = "模型类型")
    private String modelType;

    @ApiModelProperty(value = "模型描述")
    private String description;

    @ApiModelProperty(value = "模型地址")
    private String modelUrl;

    @ApiModelProperty(value = "API Key")
    private String apiKey;

    @ApiModelProperty(value = "Secret Key")
    private String secretKey;

    @ApiModelProperty(value = "参数配置")
    private String params;

    @ApiModelProperty(value = "状态：0-禁用，1-启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
