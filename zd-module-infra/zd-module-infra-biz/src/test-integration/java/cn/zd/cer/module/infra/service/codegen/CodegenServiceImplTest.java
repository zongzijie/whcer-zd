package cn.zd.cer.module.infra.service.codegen;

import cn.zd.cer.module.infra.test.BaseDbUnitTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

class CodegenServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CodegenServiceImpl codegenService;

    @Test
    public void tetCreateCodegenTable() {
        codegenService.createCodegen(0L, "infra_test_demo");
//        infraCodegenService.createCodegenTable("infra_codegen_table");
//        infraCodegenService.createCodegen("infra_codegen_column");
    }

}
