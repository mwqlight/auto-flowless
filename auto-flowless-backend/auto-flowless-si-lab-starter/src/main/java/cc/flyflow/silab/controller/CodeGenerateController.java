package cc.flyflow.silab.controller;

import cc.flyflow.silab.model.GenerateConfig;
import cc.flyflow.silab.service.CodeGenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "业务实验室 - 代码生成")
@RestController
@RequestMapping("/api/silab/code-generate")
public class CodeGenerateController {
    
    @Autowired
    private CodeGenerateService codeGenerateService;
    
    @ApiOperation("生成代码")
    @PostMapping("/generate")
    public void generateCode(@RequestBody GenerateConfig config) {
        codeGenerateService.generateCode(config);
    }
    
    @ApiOperation("获取支持的数据库类型")
    @GetMapping("/supported-databases")
    public List<String> getSupportedDatabases() {
        return codeGenerateService.getSupportedDatabases();
    }
    
    @ApiOperation("获取数据库表名列表")
    @PostMapping("/table-names")
    public List<String> getTableNames(@RequestParam String dbUrl, 
                                      @RequestParam String username, 
                                      @RequestParam String password) {
        return codeGenerateService.getTableNames(dbUrl, username, password);
    }
}
