package cc.flyflow.avstream.controller;

import cc.flyflow.avstream.entity.LiveStream;
import cc.flyflow.avstream.service.LiveStreamService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播流控制器
 */
@RestController
@RequestMapping("/api/live-stream")
@Slf4j
public class LiveStreamController {
    
    @Autowired
    private LiveStreamService liveStreamService;
    
    /**
     * 获取所有直播流
     * @return 直播流列表
     */
    @GetMapping("/list")
    public JSONObject getAllLiveStreams() {
        JSONObject result = new JSONObject();
        try {
            List<LiveStream> liveStreams = liveStreamService.getAllLiveStreams();
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", liveStreams);
        } catch (Exception e) {
            log.error("获取直播流列表失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 根据ID获取直播流
     * @param liveId 直播流ID
     * @return 直播流信息
     */
    @GetMapping("/get/{liveId}")
    public JSONObject getLiveStreamById(@PathVariable String liveId) {
        JSONObject result = new JSONObject();
        try {
            LiveStream liveStream = liveStreamService.getLiveStreamById(liveId);
            if (liveStream != null) {
                result.put("code", 200);
                result.put("message", "查询成功");
                result.put("data", liveStream);
            } else {
                result.put("code", 404);
                result.put("message", "直播流不存在");
            }
        } catch (Exception e) {
            log.error("获取直播流详情失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 添加直播流
     * @param liveStream 直播流信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public JSONObject addLiveStream(@RequestBody LiveStream liveStream) {
        JSONObject result = new JSONObject();
        try {
            boolean success = liveStreamService.addLiveStream(liveStream);
            if (success) {
                result.put("code", 200);
                result.put("message", "添加成功");
            } else {
                result.put("code", 500);
                result.put("message", "添加失败");
            }
        } catch (Exception e) {
            log.error("添加直播流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新直播流
     * @param liveStream 直播流信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public JSONObject updateLiveStream(@RequestBody LiveStream liveStream) {
        JSONObject result = new JSONObject();
        try {
            boolean success = liveStreamService.updateLiveStream(liveStream);
            if (success) {
                result.put("code", 200);
                result.put("message", "更新成功");
            } else {
                result.put("code", 500);
                result.put("message", "更新失败");
            }
        } catch (Exception e) {
            log.error("更新直播流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除直播流
     * @param liveId 直播流ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{liveId}")
    public JSONObject deleteLiveStream(@PathVariable String liveId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = liveStreamService.deleteLiveStream(liveId);
            if (success) {
                result.put("code", 200);
                result.put("message", "删除成功");
            } else {
                result.put("code", 500);
                result.put("message", "删除失败");
            }
        } catch (Exception e) {
            log.error("删除直播流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 开始直播
     * @param liveId 直播流ID
     * @return 开始结果
     */
    @PostMapping("/start/{liveId}")
    public JSONObject startLiveStream(@PathVariable String liveId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = liveStreamService.startLiveStream(liveId);
            if (success) {
                result.put("code", 200);
                result.put("message", "开始直播成功");
            } else {
                result.put("code", 500);
                result.put("message", "开始直播失败");
            }
        } catch (Exception e) {
            log.error("开始直播失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "开始直播失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 停止直播
     * @param liveId 直播流ID
     * @return 停止结果
     */
    @PostMapping("/stop/{liveId}")
    public JSONObject stopLiveStream(@PathVariable String liveId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = liveStreamService.stopLiveStream(liveId);
            if (success) {
                result.put("code", 200);
                result.put("message", "停止直播成功");
            } else {
                result.put("code", 500);
                result.put("message", "停止直播失败");
            }
        } catch (Exception e) {
            log.error("停止直播失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "停止直播失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 获取直播流观看地址
     * @param liveId 直播流ID
     * @return 观看地址
     */
    @GetMapping("/url/{liveId}")
    public JSONObject getLiveStreamUrl(@PathVariable String liveId) {
        JSONObject result = new JSONObject();
        try {
            String liveUrl = liveStreamService.getLiveStreamUrl(liveId);
            if (liveUrl != null) {
                result.put("code", 200);
                result.put("message", "获取成功");
                result.put("data", liveUrl);
            } else {
                result.put("code", 404);
                result.put("message", "直播流未运行或不存在");
            }
        } catch (Exception e) {
            log.error("获取直播观看地址失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "获取失败: " + e.getMessage());
        }
        return result;
    }
}
