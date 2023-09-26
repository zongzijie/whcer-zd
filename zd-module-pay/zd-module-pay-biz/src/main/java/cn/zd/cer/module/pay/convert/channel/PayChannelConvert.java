package cn.zd.cer.module.pay.convert.channel;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import cn.zd.cer.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import cn.zd.cer.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import cn.zd.cer.module.pay.dal.dataobject.channel.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(cn.zd.cer.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);

}
