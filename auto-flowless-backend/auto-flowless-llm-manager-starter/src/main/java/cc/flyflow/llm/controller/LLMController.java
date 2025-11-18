package cc.flyflow.llm.controller;

import cc.flyflow.llm.service.LLMService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AI大模型控制器
 * 提供REST API接口，用于大模型的上传、下载、训练和评估等操作
 */
@RestController
@RequestMapping("/api/llm")
public class LLMController {

    @Autowired
    private LLMService llmService;

    /**
     * 初始化LLM管理器
     * 
     * @return JSONObject 初始化结果
     */
    @PostMapping("/init")
    public JSONObject initLLM() {
        try {
            llmService.initLLMManager();
            return new JSONObject()
                    .set("success", true)
                    .set("message", "LLM管理器初始化成功");
        } catch (Exception e) {
            return new JSONObject()
                    .set("success", false)
                    .set("message", "LLM管理器初始化失败: " + e.getMessage());
        }
    }

    /**
     * 上传大模型
     * 
     * @param modelPath     模型路径（本地或URL）
     * @param modelName     模型名称
     * @param version       模型版本
     * @param description   模型描述
     * @return JSONObject 上传结果
     */
    @PostMapping("/upload")
    public JSONObject uploadModel(
            @RequestParam("modelPath") String modelPath,
            @RequestParam("modelName") String modelName,
            @RequestParam(value = "version", defaultValue = "1.0") String version,
            @RequestParam(value = "description", defaultValue = "") String description) {
        return llmService.uploadModel(modelPath, modelName, version, description);
    }

    /**
     * 下载大模型
     * 
     * @param modelId       模型ID
     * @param targetPath    目标路径
     * @return JSONObject 下载结果
     */
    @PostMapping("/download")
    public JSONObject downloadModel(
            @RequestParam("modelId") String modelId,
            @RequestParam("targetPath") String targetPath) {
        return llmService.downloadModel(modelId, targetPath);
    }

    /**
     * 训练大模型
     * 
     * @param modelId           模型ID
     * @param datasetPath       数据集路径
     * @param trainingParams    训练参数
     * @return JSONObject 训练结果
     */
    @PostMapping("/train")
    public JSONObject trainModel(
            @RequestParam("modelId") String modelId,
            @RequestParam("datasetPath") String datasetPath,
            @RequestBody Map<String, Object> trainingParams) {
        return llmService.trainModel(modelId, datasetPath, trainingParams);
    }

    /**
     * 评估大模型
     * 
     * @param modelId           模型ID
     * @param evalDatasetPath   评估数据集路径
     * @param evalParams        评估参数
     * @return JSONObject 评估结果
     */
    @PostMapping("/evaluate")
    public JSONObject evaluateModel(
            @RequestParam("modelId") String modelId,
            @RequestParam("evalDatasetPath") String evalDatasetPath,
            @RequestBody Map<String, Object> evalParams) {
        return llmService.evaluateModel(modelId, evalDatasetPath, evalParams);
    }
}
