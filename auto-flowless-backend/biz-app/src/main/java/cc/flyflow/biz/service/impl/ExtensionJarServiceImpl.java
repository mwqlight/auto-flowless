package cc.flyflow.biz.service.impl;

import cc.flyflow.biz.entity.ExtensionJar;
import cc.flyflow.biz.mapper.ExtensionJarMapper;
import cc.flyflow.biz.service.IExtensionJarService;
import cc.flyflow.common.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 扩展JAR包Service实现类
 * @author Vincent
 * @since 2023-12-01
 */
@Service
public class ExtensionJarServiceImpl extends ServiceImpl<ExtensionJarMapper, ExtensionJar> implements IExtensionJarService {

    @Override
    public R<List<ExtensionJar>> queryAll() {
        List<ExtensionJar> list = this.list();
        return R.success(list);
    }

    @Override
    public R<ExtensionJar> queryById(Long id) {
        ExtensionJar extensionJar = this.getById(id);
        return R.success(extensionJar);
    }

    @Override
    public R create(ExtensionJar extensionJar) {
        boolean result = this.save(extensionJar);
        return result ? R.success() : R.fail("新增失败");
    }

    @Override
    public R update(ExtensionJar extensionJar) {
        boolean result = this.updateById(extensionJar);
        return result ? R.success() : R.fail("更新失败");
    }

    @Override
    public R delete(Long id) {
        boolean result = this.removeById(id);
        return result ? R.success() : R.fail("删除失败");
    }

    @Override
    public R enable(Long id, Integer status) {
        ExtensionJar extensionJar = this.getById(id);
        if (extensionJar == null) {
            return R.fail("扩展JAR包不存在");
        }
        extensionJar.setStatus(status);
        boolean result = this.updateById(extensionJar);
        return result ? R.success() : R.fail("操作失败");
    }
}
