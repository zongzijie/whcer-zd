package cn.zd.cer.module.system.controller.admin.sms;

import cn.zd.cer.module.system.controller.admin.sms.vo.log.SmsLogExcelVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.log.SmsLogExportReqVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.log.SmsLogPageReqVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import cn.zd.cer.module.system.convert.sms.SmsLogConvert;
import cn.zd.cer.module.system.dal.dataobject.sms.SmsLogDO;
import cn.zd.cer.module.system.service.sms.SmsLogService;
import cn.zd.cer.framework.common.pojo.CommonResult;
import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.excel.core.util.ExcelUtils;
import cn.zd.cer.framework.operatelog.core.annotations.OperateLog;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.zd.cer.framework.common.pojo.CommonResult.success;
import static cn.zd.cer.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 短信日志")
@RestController
@RequestMapping("/system/sms-log")
@Validated
public class SmsLogController {

    @Resource
    private SmsLogService smsLogService;

    @GetMapping("/page")
    @Operation(summary = "获得短信日志分页")
    @PreAuthorize("@ss.hasPermission('system:sms-log:query')")
    public CommonResult<PageResult<SmsLogRespVO>> getSmsLogPage(@Valid SmsLogPageReqVO pageVO) {
        PageResult<SmsLogDO> pageResult = smsLogService.getSmsLogPage(pageVO);
        return success(SmsLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出短信日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:sms-log:export')")
    @OperateLog(type = EXPORT)
    public void exportSmsLogExcel(@Valid SmsLogExportReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        List<SmsLogDO> list = smsLogService.getSmsLogList(exportReqVO);
        // 导出 Excel
        List<SmsLogExcelVO> datas = SmsLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "短信日志.xls", "数据", SmsLogExcelVO.class, datas);
    }

}
