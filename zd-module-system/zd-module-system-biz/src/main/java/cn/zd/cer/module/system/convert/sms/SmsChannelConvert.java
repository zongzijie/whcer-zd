package cn.zd.cer.module.system.convert.sms;

import cn.zd.cer.module.system.controller.admin.sms.vo.channel.SmsChannelCreateReqVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.channel.SmsChannelRespVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.channel.SmsChannelSimpleRespVO;
import cn.zd.cer.module.system.controller.admin.sms.vo.channel.SmsChannelUpdateReqVO;
import cn.zd.cer.module.system.dal.dataobject.sms.SmsChannelDO;
import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.sms.core.property.SmsChannelProperties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信渠道 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SmsChannelConvert {

    SmsChannelConvert INSTANCE = Mappers.getMapper(SmsChannelConvert.class);

    SmsChannelDO convert(SmsChannelCreateReqVO bean);

    SmsChannelDO convert(SmsChannelUpdateReqVO bean);

    SmsChannelRespVO convert(SmsChannelDO bean);

    List<SmsChannelRespVO> convertList(List<SmsChannelDO> list);

    PageResult<SmsChannelRespVO> convertPage(PageResult<SmsChannelDO> page);

    List<SmsChannelSimpleRespVO> convertList03(List<SmsChannelDO> list);

    SmsChannelProperties convert02(SmsChannelDO channel);

}
