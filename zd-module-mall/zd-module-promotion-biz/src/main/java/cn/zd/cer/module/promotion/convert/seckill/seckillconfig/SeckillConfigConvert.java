package cn.zd.cer.module.promotion.convert.seckill.seckillconfig;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.config.SeckillConfigCreateReqVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.config.SeckillConfigRespVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.config.SeckillConfigSimpleRespVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.config.SeckillConfigUpdateReqVO;
import cn.zd.cer.module.promotion.dal.dataobject.seckill.seckillconfig.SeckillConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 秒杀时段 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SeckillConfigConvert {

    SeckillConfigConvert INSTANCE = Mappers.getMapper(SeckillConfigConvert.class);

    SeckillConfigDO convert(SeckillConfigCreateReqVO bean);

    SeckillConfigDO convert(SeckillConfigUpdateReqVO bean);

    SeckillConfigRespVO convert(SeckillConfigDO bean);

    List<SeckillConfigRespVO> convertList(List<SeckillConfigDO> list);

    List<SeckillConfigSimpleRespVO> convertList1(List<SeckillConfigDO> list);

    PageResult<SeckillConfigRespVO> convertPage(PageResult<SeckillConfigDO> page);

}
