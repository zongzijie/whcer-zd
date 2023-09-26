package cn.zd.cer.module.system.convert.notify;

import java.util.*;

import cn.zd.cer.framework.common.pojo.PageResult;

import cn.zd.cer.framework.common.util.date.DateUtils;
import cn.zd.cer.module.system.controller.admin.notify.vo.template.NotifyTemplateCreateReqVO;
import cn.zd.cer.module.system.controller.admin.notify.vo.template.NotifyTemplateRespVO;
import cn.zd.cer.module.system.controller.admin.notify.vo.template.NotifyTemplateUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.zd.cer.module.system.dal.dataobject.notify.NotifyTemplateDO;

/**
 * 站内信模版 Convert
 *
 * @author xrcoder
 */
@Mapper(uses = DateUtils.class)
public interface NotifyTemplateConvert {

    NotifyTemplateConvert INSTANCE = Mappers.getMapper(NotifyTemplateConvert.class);

    NotifyTemplateDO convert(NotifyTemplateCreateReqVO bean);

    NotifyTemplateDO convert(NotifyTemplateUpdateReqVO bean);

    NotifyTemplateRespVO convert(NotifyTemplateDO bean);

    List<NotifyTemplateRespVO> convertList(List<NotifyTemplateDO> list);

    PageResult<NotifyTemplateRespVO> convertPage(PageResult<NotifyTemplateDO> page);

}
