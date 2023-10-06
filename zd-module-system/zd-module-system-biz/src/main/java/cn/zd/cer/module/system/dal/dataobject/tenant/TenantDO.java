package cn.zd.cer.module.system.dal.dataobject.tenant;

import cn.zd.cer.framework.common.enums.CommonStatusEnum;
import cn.zd.cer.framework.mybatis.core.dataobject.BaseDO;
import cn.zd.cer.module.system.dal.dataobject.user.AdminUserDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 租户 DO
 *
 * @author 芋道源码
 */
@TableName(value = "system_tenant", autoResultMap = true)
@KeySequence("system_tenant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantDO extends BaseDO {

    /**
     * 套餐编号 - 系统
     */
    public static final Long PACKAGE_ID_SYSTEM = 0L;

    /**
     * 租户编号，自增
     */
    private Long id;
    /**
     * 租户名，唯一
     */
    private String name;
    /**
     * 联系人的用户编号
     *
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long contactUserId;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系手机
     */
    private String contactMobile;
    /**
     * 租户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 绑定域名
     *
     * TODO 芋艿：目前是预留字段，未来会支持根据域名，自动查询到对应的租户。等等
     */
    private String domain;
    /**
     * 租户套餐编号
     *
     * 关联 {@link TenantPackageDO#getId()}
     * 特殊逻辑：系统内置租户，不使用套餐，暂时使用 {@link #PACKAGE_ID_SYSTEM} 标识
     */
    private Long packageId;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 账号数量
     */
    private Integer accountCount;
    /**
     * 联系人身份证号
     */
    private String contactIdCard;
    /**
     * 联系人授权书
     */
    private String contactAuthorizeFile;
    /**
     * 联系人身份证正面
     */
    private String contactIdCardFileFront;
    /**
     * 联系人身份证反面
     */
    private String contactIdCardFileBack;
    /**
     * 是否三证合一
     */
    private Boolean threeInOne;
    /**
     * 统一社会信用代码
     */
    private String uscc;
    /**
     * 营业执照注册号
     */
    private String bln;
    /**
     * 营业执照
     */
    private String blnFile;
    /**
     * 组织机构代码
     */
    private String unitCode;
    /**
     * 组织机构代码证
     */
    private String unitCodeFile;
    /**
     * 税务登记证号
     */
    private String taxId;
    /**
     * 税务登记证
     */
    private String taxIdFile;
    /**
     * 所属行业编码
     */
    private String industry;
    /**
     * 所属行业名称
     */
    private String industryText;
    /**
     * 注册日期
     */
    private LocalDateTime registrationDate;
    /**
     * 注册资本（万元）
     */
    private Integer registeredCapital;
    /**
     * 注册省份
     */
    private String registeredProvince;
    /**
     * 注册城市
     */
    private String registeredCity;
    /**
     * 注册区县
     */
    private String registeredCounty;
    /**
     * 注册地址
     */
    private String registeredAddress;
    /**
     * 法人信息
     */
    private String corporate;
    /**
     * 法人身份证号
     */
    private String corporateIdCard;
    /**
     * 法人身份证正面
     */
    private String corporateIdCardFileFront;
    /**
     * 法人身份证反面
     */
    private String corporateIdCardFileBack;
    /**
     * 企业性质
     */
    private String enterpriseNature;

}
