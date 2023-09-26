package cn.zd.cer.module.member.controller.app.point;

import cn.zd.cer.framework.common.pojo.CommonResult;
import cn.zd.cer.framework.common.pojo.PageParam;
import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.security.core.annotations.PreAuthenticated;
import cn.zd.cer.module.member.controller.app.point.vo.AppMemberPointRecordRespVO;
import cn.zd.cer.module.member.convert.point.MemberPointRecordConvert;
import cn.zd.cer.module.member.dal.dataobject.point.MemberPointRecordDO;
import cn.zd.cer.module.member.service.point.MemberPointRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.zd.cer.framework.common.pojo.CommonResult.success;
import static cn.zd.cer.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 签到记录")
@RestController
@RequestMapping("/member/point/record")
@Validated
public class AppMemberPointRecordController {

    @Resource
    private MemberPointRecordService pointRecordService;

    @GetMapping("/page")
    @Operation(summary = "获得用户积分记录分页")
    @PreAuthenticated
    public CommonResult<PageResult<AppMemberPointRecordRespVO>> getPointRecordPage(@Valid PageParam pageVO) {
        PageResult<MemberPointRecordDO> pageResult = pointRecordService.getPointRecordPage(getLoginUserId(), pageVO);
        return success(MemberPointRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
