package cn.zd.cer.module.system.convert.mail;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.system.controller.admin.mail.vo.log.MailLogRespVO;
import cn.zd.cer.module.system.dal.dataobject.mail.MailLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailLogConvert {

    MailLogConvert INSTANCE = Mappers.getMapper(MailLogConvert.class);

    PageResult<MailLogRespVO> convertPage(PageResult<MailLogDO> pageResult);

    MailLogRespVO convert(MailLogDO bean);

}
