package cn.zd.cer.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ClassUtils;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author 芋道源码
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n----------------------------------------------------------\n\t" +
                            "项目启动成功！\n\t" +
                            "接口文档: \t{} \n\t" +
                            "开发文档: \t{} \n\t" +
                            "视频教程: \t{} \n" +
                            "----------------------------------------------------------",
                    "https://doc.iocoder.cn/api-doc/",
                    "https://doc.iocoder.cn",
                    "https://t.zsxq.com/02Yf6M7Qn");

            // 数据报表
            if (isNotPresent("cn.zd.cer.module.report.framework.security.config.SecurityConfiguration")) {
                System.out.println("[报表模块 zd-module-report - 已禁用][参考 https://doc.iocoder.cn/report/ 开启]");
            }
            // 工作流
            if (isNotPresent("cn.zd.cer.framework.flowable.config.ZdFlowableConfiguration")) {
                System.out.println("[工作流模块 zd-module-bpm - 已禁用][参考 https://doc.iocoder.cn/bpm/ 开启]");
            }
            // 微信公众号
            if (isNotPresent("cn.zd.cer.module.mp.framework.mp.config.MpConfiguration")) {
                System.out.println("[微信公众号 zd-module-mp - 已禁用][参考 https://doc.iocoder.cn/mp/build/ 开启]");
            }
            // 商城系统
            if (isNotPresent("cn.zd.cer.module.trade.framework.web.config.TradeWebConfiguration")) {
                System.out.println("[商城系统 zd-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
            }
            // 支付平台
            if (isNotPresent("cn.zd.cer.module.pay.framework.pay.config.PayConfiguration")) {
                System.out.println("[支付系统 zd-module-pay - 已禁用][参考 https://doc.iocoder.cn/pay/build/ 开启]");
            }
        });
    }

    private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }

}