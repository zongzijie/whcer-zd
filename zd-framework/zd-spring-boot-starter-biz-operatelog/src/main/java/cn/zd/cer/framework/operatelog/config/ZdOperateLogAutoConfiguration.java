package cn.zd.cer.framework.operatelog.config;

import cn.zd.cer.framework.operatelog.core.aop.OperateLogAspect;
import cn.zd.cer.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.zd.cer.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import cn.zd.cer.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ZdOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
