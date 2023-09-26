package cn.zd.cer.module.promotion.convert.bargain;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.promotion.controller.admin.bargain.vo.BargainActivityBaseVO;
import cn.zd.cer.module.promotion.controller.admin.bargain.vo.BargainActivityRespVO;
import cn.zd.cer.module.promotion.controller.admin.bargain.vo.BargainActivityUpdateReqVO;
import cn.zd.cer.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 拼团活动 Convert
 *
 * @author HUIHUI
 */
@Mapper
public interface BargainActivityConvert {

    BargainActivityConvert INSTANCE = Mappers.getMapper(BargainActivityConvert.class);

    BargainActivityDO convert(BargainActivityBaseVO bean);

    BargainActivityDO convert(BargainActivityUpdateReqVO bean);

    BargainActivityRespVO convert(BargainActivityDO bean);

    List<BargainActivityRespVO> convertList(List<BargainActivityDO> list);

    PageResult<BargainActivityRespVO> convertPage(PageResult<BargainActivityDO> page);

}
