package cc.flyflow.biz.service;

import cc.flyflow.common.dto.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cc.flyflow.biz.entity.ExtensionJar;

import java.util.List;

/**
 * 扩展JAR包Service接口
 * @author Vincent
 * @since 2023-12-01
 */
public interface IExtensionJarService extends IService<ExtensionJar> {

    /**
     * 查询所有扩展JAR包
     * @return 扩展JAR包列表
     */
    R<List<ExtensionJar>> queryAll();

    /**
     * 根据ID查询扩展JAR包
     * @param id 扩展JAR包ID
     * @return 扩展JAR包
     */
    R<ExtensionJar> queryById(Long id);

    /**
     * 新增扩展JAR包
     * @param extensionJar 扩展JAR包
     * @return 新增结果
     */
    R create(ExtensionJar extensionJar);

    /**
     * 更新扩展JAR包
     * @param extensionJar 扩展JAR包
     * @return 更新结果
     */
    R update(ExtensionJar extensionJar);

    /**
     * 删除扩展JAR包
     * @param id 扩展JAR包ID
     * @return 删除结果
     */
    R delete(Long id);

    /**
     * 启用/禁用扩展JAR包
     * @param id 扩展JAR包ID
     * @param status 状态：0-禁用，1-启用
     * @return 操作结果
     */
    R enable(Long id, Integer status);
}
