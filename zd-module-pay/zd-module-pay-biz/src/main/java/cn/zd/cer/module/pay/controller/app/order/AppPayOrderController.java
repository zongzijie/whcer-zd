package cn.zd.cer.module.pay.controller.app.order;

import cn.zd.cer.framework.common.pojo.CommonResult;
import cn.zd.cer.module.pay.controller.admin.order.vo.PayOrderRespVO;
import cn.zd.cer.module.pay.controller.admin.order.vo.PayOrderSubmitRespVO;
import cn.zd.cer.module.pay.controller.app.order.vo.AppPayOrderSubmitReqVO;
import cn.zd.cer.module.pay.controller.app.order.vo.AppPayOrderSubmitRespVO;
import cn.zd.cer.module.pay.convert.order.PayOrderConvert;
import cn.zd.cer.module.pay.service.order.PayOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static cn.zd.cer.framework.common.pojo.CommonResult.success;
import static cn.zd.cer.framework.common.util.servlet.ServletUtils.getClientIP;

@Tag(name = "用户 APP - 支付订单")
@RestController
@RequestMapping("/pay/order")
@Validated
@Slf4j
public class AppPayOrderController {

    @Resource
    private PayOrderService payOrderService;

    // TODO 芋艿：临时 demo，技术打样。
    @GetMapping("/get")
    @Operation(summary = "获得支付订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<PayOrderRespVO> getOrder(@RequestParam("id") Long id) {
        return success(PayOrderConvert.INSTANCE.convert(payOrderService.getOrder(id)));
    }

    @PostMapping("/submit")
    @Operation(summary = "提交支付订单")
    public CommonResult<AppPayOrderSubmitRespVO> submitPayOrder(@RequestBody AppPayOrderSubmitReqVO reqVO) {
        PayOrderSubmitRespVO respVO = payOrderService.submitOrder(reqVO, getClientIP());
        return success(PayOrderConvert.INSTANCE.convert3(respVO));
    }

}
