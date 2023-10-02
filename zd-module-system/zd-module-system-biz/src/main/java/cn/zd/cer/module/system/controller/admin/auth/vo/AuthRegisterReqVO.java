package cn.zd.cer.module.system.controller.admin.auth.vo;

import cn.hutool.core.util.StrUtil;
import cn.zd.cer.framework.common.validation.InEnum;
import cn.zd.cer.framework.common.validation.Mobile;
import cn.zd.cer.module.system.enums.social.SocialTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "管理后台 - 用户账号注册 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRegisterReqVO {

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    @NotEmpty(message = "用户昵称不能为空")
    private String nickname;

    @Schema(description = "手机号码", example = "15601691300")
    @NotEmpty(message = "手机号码不能为空")
    @Mobile
    private String mobile;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;


    @Schema(description = "短信验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotEmpty(message = "验证码不能为空")
    private String code;

}