package cn.zd.cer.module.system.controller.admin.tenant.vo.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * 租户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TenantBaseVO {

    @Schema(description = "租户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    @NotNull(message = "租户名不能为空")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "联系人不能为空")
    private String contactName;

    @Schema(description = "联系手机", example = "15601691300")
    private String contactMobile;

    @Schema(description = "租户状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "租户状态")
    private Integer status;

    @Schema(description = "绑定域名", example = "https://www.iocoder.cn")
    private String domain;

    @Schema(description = "租户套餐编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "租户套餐编号不能为空")
    private Long packageId;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "过期时间不能为空")
    private LocalDateTime expireTime;

    @Schema(description = "账号数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "账号数量不能为空")
    private Integer accountCount;

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
