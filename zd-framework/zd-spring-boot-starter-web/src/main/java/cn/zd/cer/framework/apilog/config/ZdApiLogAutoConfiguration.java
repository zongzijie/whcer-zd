package cn.zd.cer.framework.apilog.config;

import cn.zd.cer.framework.apilog.core.filter.ApiAccessLogFilter;
import cn.zd.cer.framework.apilog.core.service.ApiAccessLogFrameworkService;
import cn.zd.cer.framework.apilog.core.service.ApiAccessLogFrameworkServiceImpl;
import cn.zd.cer.framework.apilog.core.service.ApiErrorLogFrameworkService;
import cn.zd.cer.framework.apilog.core.service.ApiErrorLogFrameworkServiceImpl;
import cn.zd.cer.framework.common.enums.WebFilterOrderEnum;
import cn.zd.cer.framework.web.config.WebProperties;
import cn.zd.cer.framework.web.config.ZdWebAutoConfiguration;
import cn.zd.cer.module.infra.api.logger.ApiAccessLogApi;
import cn.zd.cer.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@AutoConfiguration(after = ZdWebAutoConfiguration.class)
public class ZdApiLogAutoConfiguration {

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "zd.access-log", value = "enable", matchIfMissing = true) // 允许使用 zd.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
