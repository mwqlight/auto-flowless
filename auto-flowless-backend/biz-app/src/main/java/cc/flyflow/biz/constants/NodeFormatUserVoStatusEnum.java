package cc.flyflow.biz.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum NodeFormatUserVoStatusEnum {

    WKS(0, "未开始"),
    JXZ(1, "进行中"),
    YJS(2, "已结束"),
    YCX(3, "已撤销"),
    ;

    private Integer code;

    private String name;

    NodeFormatUserVoStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static NodeFormatUserVoStatusEnum get(int code){
        NodeFormatUserVoStatusEnum nodeFormatUserVoStatusEnum = Arrays.stream(NodeFormatUserVoStatusEnum.values()).filter(w -> w.getCode() == code).findAny().get();
        return nodeFormatUserVoStatusEnum;
    }

}
