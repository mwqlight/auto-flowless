package cc.flyflow.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * AI模型实体类
 *
 * @author flyflow
 * @since 2023-07-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("flyflow_ai_model")
@ApiModel(value = "AiModel对象", description = "AI模型")
public class AiModel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("模型名称")
    private String name;

    @ApiModelProperty("模型类型")
    private String type;

    @ApiModelProperty("模型版本")
    private String version;

    @ApiModelProperty("API地址")
    private String apiUrl;

    @ApiModelProperty("API密钥")
    private String apiKey;

    @ApiModelProperty("模型描述")
    private String description;

    @ApiModelProperty("是否启用")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;

}
