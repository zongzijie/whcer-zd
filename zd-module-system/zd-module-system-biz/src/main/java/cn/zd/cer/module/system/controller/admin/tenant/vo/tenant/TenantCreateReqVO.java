package cn.zd.cer.module.system.controller.admin.tenant.vo.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 租户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TenantCreateReqVO extends TenantBaseVO {

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "zd")
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @Schema(description = "联系人身份证号")
    private String contactIdCard;

    @Schema(description = "联系人授权书")
    private String contactAuthorizeFile;

    @Schema(description = "联系人身份证正面")
    private String contactIdCardFileFront;

    @Schema(description = "联系人身份证反面")
    private String contactIdCardFileBack;

    @Schema(description = "是否三证合一")
    private Boolean threeInOne;

    @Schema(description = "统一社会信用代码")
    private String uscc;

    @Schema(description = "营业执照注册号")
    private String bln;

    @Schema(description = "营业执照")
    private String blnFile;

    @Schema(description = "组织机构代码")
    private String unitCode;

    @Schema(description = "组织机构代码证")
    private String unitCodeFile;

    @Schema(description = "税务登记证号", example = "25345")
    private String taxId;

    @Schema(description = "税务登记证")
    private String taxIdFile;

    @Schema(description = "所属行业编码")
    private String industry;

    @Schema(description = "所属行业名称")
    private String industryText;

    @Schema(description = "注册日期")
    private LocalDateTime registrationDate;

    @Schema(description = "注册资本（万元）")
    private Integer registeredCapital;

    @Schema(description = "注册省份")
    private String registeredProvince;

    @Schema(description = "注册城市")
    private String registeredCity;

    @Schema(description = "注册区县")
    private String registeredCounty;

    @Schema(description = "注册地址")
    private String registeredAddress;

    @Schema(description = "法人信息")
    private String corporate;

    @Schema(description = "法人身份证号")
    private String corporateIdCard;

    @Schema(description = "法人身份证正面")
    private String corporateIdCardFileFront;

    @Schema(description = "法人身份证反面")
    private String corporateIdCardFileBack;

    @Schema(description = "企业性质")
    private String enterpriseNature;

}
