package cc.flyflow.llm.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * AI大模型服务类
 * 提供大模型的上传、下载、训练和评估等服务接口
 */
@Slf4j
@Service
public class LLMService {

    @Autowired
    private PythonInterpreter pythonInterpreter;

    /**
     * 初始化Python大模型管理器
     */
    public void initLLMManager() {
        try {
            // 导入Python模块
            pythonInterpreter.exec("from llm_manager import LLMManager");
            // 初始化LLM管理器
            pythonInterpreter.exec("config = {'model_storage_path': './models'}");
            pythonInterpreter.exec("llm_manager = LLMManager(config)");
            log.info("Python LLM管理器初始化成功");
        } catch (Exception e) {
            log.error("Python LLM管理器初始化失败", e);
            throw new RuntimeException("初始化LLM管理器失败", e);
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
    public JSONObject uploadModel(String modelPath, String modelName, String version, String description) {
        try {
            // 执行Python上传模型方法
            pythonInterpreter.set("model_path", modelPath);
            pythonInterpreter.set("model_name", modelName);
            pythonInterpreter.set("version", version);
            pythonInterpreter.set("description", description);

            pythonInterpreter.exec(
                "result = llm_manager.upload_model(model_path, model_name, version, description)"
            );

            // 获取执行结果
            String resultJson = pythonInterpreter.eval("result").toString();
            return JSONUtil.parseObj(resultJson);
        } catch (Exception e) {
            log.error("上传模型失败", e);
            return JSONUtil.createObj()
                    .set("success", false)
                    .set("message", "上传模型失败: " + e.getMessage());
        }
    }

    /**
     * 下载大模型
     * 
     * @param modelId       模型ID
     * @param targetPath    目标路径
     * @return JSONObject 下载结果
     */
    public JSONObject downloadModel(String modelId, String targetPath) {
        try {
            // 执行Python下载模型方法
            pythonInterpreter.set("model_id", modelId);
            pythonInterpreter.set("target_path", targetPath);

            pythonInterpreter.exec(
                "result = llm_manager.download_model(model_id, target_path)"
            );

            // 获取执行结果
            String resultJson = pythonInterpreter.eval("result").toString();
            return JSONUtil.parseObj(resultJson);
        } catch (Exception e) {
            log.error("下载模型失败", e);
            return JSONUtil.createObj()
                    .set("success", false)
                    .set("message", "下载模型失败: " + e.getMessage());
        }
    }

    /**
     * 训练大模型
     * 
     * @param modelId           模型ID
     * @param datasetPath       数据集路径
     * @param trainingParams    训练参数
     * @return JSONObject 训练结果
     */
    public JSONObject trainModel(String modelId, String datasetPath, Map<String, Object> trainingParams) {
        try {
            // 执行Python训练模型方法
            pythonInterpreter.set("model_id", modelId);
            pythonInterpreter.set("dataset_path", datasetPath);
            pythonInterpreter.set("training_params", trainingParams);

            pythonInterpreter.exec(
                "result = llm_manager.train_model(model_id, dataset_path, training_params)"
            );

            // 获取执行结果
            String resultJson = pythonInterpreter.eval("result").toString();
            return JSONUtil.parseObj(resultJson);
        } catch (Exception e) {
            log.error("训练模型失败", e);
            return JSONUtil.createObj()
                    .set("success", false)
                    .set("message", "训练模型失败: " + e.getMessage());
        }
    }

    /**
     * 评估大模型
     * 
     * @param modelId           模型ID
     * @param evalDatasetPath   评估数据集路径
     * @param evalParams        评估参数
     * @return JSONObject 评估结果
     */
    public JSONObject evaluateModel(String modelId, String evalDatasetPath, Map<String, Object> evalParams) {
        try {
            // 执行Python评估模型方法
            pythonInterpreter.set("model_id", modelId);
            pythonInterpreter.set("eval_dataset_path", evalDatasetPath);
            pythonInterpreter.set("eval_params", evalParams);

            pythonInterpreter.exec(
                "result = llm_manager.evaluate_model(model_id, eval_dataset_path, eval_params)"
            );

            // 获取执行结果
            String resultJson = pythonInterpreter.eval("result").toString();
            return JSONUtil.parseObj(resultJson);
        } catch (Exception e) {
            log.error("评估模型失败", e);
            return JSONUtil.createObj()
                    .set("success", false)
                    .set("message", "评估模型失败: " + e.getMessage());
        }
    }
}
