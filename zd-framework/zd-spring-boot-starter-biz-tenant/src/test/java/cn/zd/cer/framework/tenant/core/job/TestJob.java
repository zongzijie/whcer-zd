package cn.zd.cer.framework.tenant.core.job;

import cn.hutool.core.collection.CollUtil;
import cn.zd.cer.framework.quartz.core.handler.JobHandler;
import cn.zd.cer.framework.tenant.core.context.TenantContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TestJob implements JobHandler {

    private final List<Long> tenantIds = new CopyOnWriteArrayList<>();

    @Override
    @TenantJob // 标记多租户
    public String execute(String param) throws Exception {
        tenantIds.add(TenantContextHolder.getTenantId());
        return "success";
    }

    public List<Long> getTenantIds() {
        CollUtil.sort(tenantIds, Long::compareTo);
        return tenantIds;
    }

}
