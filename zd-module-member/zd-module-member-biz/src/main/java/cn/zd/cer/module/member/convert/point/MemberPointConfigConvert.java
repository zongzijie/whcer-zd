package cn.zd.cer.module.member.convert.point;

import cn.zd.cer.module.member.controller.admin.point.vo.config.MemberPointConfigRespVO;
import cn.zd.cer.module.member.controller.admin.point.vo.config.MemberPointConfigSaveReqVO;
import cn.zd.cer.module.member.dal.dataobject.point.MemberPointConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 会员积分配置 Convert
 *
 * @author QingX
 */
@Mapper
public interface MemberPointConfigConvert {

    MemberPointConfigConvert INSTANCE = Mappers.getMapper(MemberPointConfigConvert.class);

    MemberPointConfigRespVO convert(MemberPointConfigDO bean);

    MemberPointConfigDO convert(MemberPointConfigSaveReqVO bean);

}
