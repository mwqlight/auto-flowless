package cc.flyflow.avstream.controller;

import cc.flyflow.avstream.entity.VideoStream;
import cc.flyflow.avstream.service.VideoStreamService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频流控制器
 */
@RestController
@RequestMapping("/api/video-stream")
@Slf4j
public class VideoStreamController {
    
    @Autowired
    private VideoStreamService videoStreamService;
    
    /**
     * 获取所有视频流
     * @return 视频流列表
     */
    @GetMapping("/list")
    public JSONObject getAllVideoStreams() {
        JSONObject result = new JSONObject();
        try {
            List<VideoStream> videoStreams = videoStreamService.getAllVideoStreams();
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", videoStreams);
        } catch (Exception e) {
            log.error("获取视频流列表失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 根据ID获取视频流
     * @param streamId 视频流ID
     * @return 视频流信息
     */
    @GetMapping("/get/{streamId}")
    public JSONObject getVideoStreamById(@PathVariable String streamId) {
        JSONObject result = new JSONObject();
        try {
            VideoStream videoStream = videoStreamService.getVideoStreamById(streamId);
            if (videoStream != null) {
                result.put("code", 200);
                result.put("message", "查询成功");
                result.put("data", videoStream);
            } else {
                result.put("code", 404);
                result.put("message", "视频流不存在");
            }
        } catch (Exception e) {
            log.error("获取视频流详情失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 添加视频流
     * @param videoStream 视频流信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public JSONObject addVideoStream(@RequestBody VideoStream videoStream) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.addVideoStream(videoStream);
            if (success) {
                result.put("code", 200);
                result.put("message", "添加成功");
            } else {
                result.put("code", 500);
                result.put("message", "添加失败");
            }
        } catch (Exception e) {
            log.error("添加视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新视频流
     * @param videoStream 视频流信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public JSONObject updateVideoStream(@RequestBody VideoStream videoStream) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.updateVideoStream(videoStream);
            if (success) {
                result.put("code", 200);
                result.put("message", "更新成功");
            } else {
                result.put("code", 500);
                result.put("message", "更新失败");
            }
        } catch (Exception e) {
            log.error("更新视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除视频流
     * @param streamId 视频流ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{streamId}")
    public JSONObject deleteVideoStream(@PathVariable String streamId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.deleteVideoStream(streamId);
            if (success) {
                result.put("code", 200);
                result.put("message", "删除成功");
            } else {
                result.put("code", 500);
                result.put("message", "删除失败");
            }
        } catch (Exception e) {
            log.error("删除视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 播放视频流
     * @param streamId 视频流ID
     * @return 播放结果
     */
    @PostMapping("/play/{streamId}")
    public JSONObject playVideoStream(@PathVariable String streamId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.playVideoStream(streamId);
            if (success) {
                result.put("code", 200);
                result.put("message", "播放成功");
            } else {
                result.put("code", 500);
                result.put("message", "播放失败");
            }
        } catch (Exception e) {
            log.error("播放视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "播放失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 暂停视频流
     * @param streamId 视频流ID
     * @return 暂停结果
     */
    @PostMapping("/pause/{streamId}")
    public JSONObject pauseVideoStream(@PathVariable String streamId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.pauseVideoStream(streamId);
            if (success) {
                result.put("code", 200);
                result.put("message", "暂停成功");
            } else {
                result.put("code", 500);
                result.put("message", "暂停失败");
            }
        } catch (Exception e) {
            log.error("暂停视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "暂停失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 快进视频流
     * @param streamId 视频流ID
     * @param seconds 快进秒数
     * @return 快进结果
     */
    @PostMapping("/fast-forward/{streamId}")
    public JSONObject fastForwardVideoStream(@PathVariable String streamId, @RequestParam long seconds) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.fastForwardVideoStream(streamId, seconds);
            if (success) {
                result.put("code", 200);
                result.put("message", "快进成功");
            } else {
                result.put("code", 500);
                result.put("message", "快进失败");
            }
        } catch (Exception e) {
            log.error("快进视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "快进失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 快退视频流
     * @param streamId 视频流ID
     * @param seconds 快退秒数
     * @return 快退结果
     */
    @PostMapping("/fast-backward/{streamId}")
    public JSONObject fastBackwardVideoStream(@PathVariable String streamId, @RequestParam long seconds) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.fastBackwardVideoStream(streamId, seconds);
            if (success) {
                result.put("code", 200);
                result.put("message", "快退成功");
            } else {
                result.put("code", 500);
                result.put("message", "快退失败");
            }
        } catch (Exception e) {
            log.error("快退视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "快退失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 停止视频流
     * @param streamId 视频流ID
     * @return 停止结果
     */
    @PostMapping("/stop/{streamId}")
    public JSONObject stopVideoStream(@PathVariable String streamId) {
        JSONObject result = new JSONObject();
        try {
            boolean success = videoStreamService.stopVideoStream(streamId);
            if (success) {
                result.put("code", 200);
                result.put("message", "停止成功");
            } else {
                result.put("code", 500);
                result.put("message", "停止失败");
            }
        } catch (Exception e) {
            log.error("停止视频流失败: {}", e.getMessage(), e);
            result.put("code", 500);
            result.put("message", "停止失败: " + e.getMessage());
        }
        return result;
    }
}
