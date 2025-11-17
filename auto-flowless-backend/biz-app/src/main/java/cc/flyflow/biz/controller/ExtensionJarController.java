package cc.flyflow.biz.controller;

import cc.flyflow.biz.entity.ExtensionJar;
import cc.flyflow.biz.service.IExtensionJarService;
import cc.flyflow.common.dto.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 扩展JAR包Controller
 * @author Vincent
 * @since 2023-12-01
 */
@RestController
@RequestMapping("/extension/jar")
@Api(tags = "扩展JAR包管理")
public class ExtensionJarController {

    @Autowired
    private IExtensionJarService extensionJarService;

    @ApiOperation("查询所有扩展JAR包")
    @GetMapping("/all")
    public R<List<ExtensionJar>> queryAll() {
        return extensionJarService.queryAll();
    }

    @ApiOperation("根据ID查询扩展JAR包")
    @GetMapping("/{id}")
    public R<ExtensionJar> queryById(@PathVariable Long id) {
        return extensionJarService.queryById(id);
    }

    @ApiOperation("新增扩展JAR包")
    @PostMapping
    public R create(@RequestBody ExtensionJar extensionJar) {
        return extensionJarService.create(extensionJar);
    }

    @ApiOperation("更新扩展JAR包")
    @PutMapping
    public R update(@RequestBody ExtensionJar extensionJar) {
        return extensionJarService.update(extensionJar);
    }

    @ApiOperation("删除扩展JAR包")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        return extensionJarService.delete(id);
    }

    @ApiOperation("启用/禁用扩展JAR包")
    @PutMapping("/enable/{id}")
    public R enable(@PathVariable Long id, @RequestParam Integer status) {
        return extensionJarService.enable(id, status);
    }
}
