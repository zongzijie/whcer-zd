package cn.zd.cer.module.promotion.convert.decorate;

import cn.zd.cer.module.promotion.controller.admin.decorate.vo.DecorateComponentRespVO;
import cn.zd.cer.module.promotion.controller.admin.decorate.vo.DecorateComponentSaveReqVO;
import cn.zd.cer.module.promotion.controller.app.decorate.vo.AppDecorateComponentRespVO;
import cn.zd.cer.module.promotion.dal.dataobject.decorate.DecorateComponentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DecorateComponentConvert {

    DecorateComponentConvert INSTANCE = Mappers.getMapper(DecorateComponentConvert.class);

    List<DecorateComponentRespVO> convertList02(List<DecorateComponentDO> list);

    DecorateComponentDO convert(DecorateComponentSaveReqVO bean);

    List<AppDecorateComponentRespVO> convertList(List<DecorateComponentDO> list);

}
