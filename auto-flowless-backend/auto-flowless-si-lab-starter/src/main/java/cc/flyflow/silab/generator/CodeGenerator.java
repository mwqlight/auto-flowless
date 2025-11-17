package cc.flyflow.silab.generator;

import cc.flyflow.silab.model.GenerateConfig;
import java.util.List;

public interface CodeGenerator {
    void generate(GenerateConfig config);
    List<String> getSupportedDatabases();
}
