package cn.zd.cer.module.member.service.signin;

import cn.zd.cer.framework.common.pojo.PageParam;
import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.member.controller.admin.signin.vo.record.MemberSignInRecordPageReqVO;
import cn.zd.cer.module.member.dal.dataobject.signin.MemberSignInRecordDO;

/**
 * 签到记录 Service 接口
 *
 * @author 芋道源码
 */
public interface MemberSignInRecordService {

    /**
     * 【管理员】获得签到记录分页
     *
     * @param pageReqVO 分页查询
     * @return 签到记录分页
     */
    PageResult<MemberSignInRecordDO> getSignInRecordPage(MemberSignInRecordPageReqVO pageReqVO);

    /**
     * 【会员】获得签到记录分页
     *
     * @param userId 用户编号
     * @param pageParam 分页查询
     * @return 签到记录分页
     */
    PageResult<MemberSignInRecordDO> getSignRecordPage(Long userId, PageParam pageParam);

}
