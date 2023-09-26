package cn.zd.cer.module.system.controller.admin.logger.vo.loginlog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.zd.cer.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 登录日志分页列表 Request VO")
@Data
public class LoginLogExportReqVO {

    @Schema(description = "用户 IP，模拟匹配", example = "127.0.0.1")
    private String userIp;

    @Schema(description = "用户账号，模拟匹配", example = "芋道")
    private String username;

    @Schema(description = "操作状态", example = "true")
    private Boolean status;

    @Schema(description = "登录时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
