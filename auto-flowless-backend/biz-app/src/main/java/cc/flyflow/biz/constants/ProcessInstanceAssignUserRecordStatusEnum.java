package cc.flyflow.biz.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 任务执行人状态
 */
@Getter
public enum ProcessInstanceAssignUserRecordStatusEnum {

    JXZ(1, "进行中"),
    YJS(2, "已结束"),
    WCL(3, "未处理");

    private int code;
    private String name;

    ProcessInstanceAssignUserRecordStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ProcessInstanceAssignUserRecordStatusEnum get(int code){
       return Arrays.stream(ProcessInstanceAssignUserRecordStatusEnum.values()).filter(w->w.getCode()==code).findAny().get();
    }

}
