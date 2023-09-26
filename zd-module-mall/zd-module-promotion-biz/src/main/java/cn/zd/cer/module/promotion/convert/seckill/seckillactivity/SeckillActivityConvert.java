package cn.zd.cer.module.promotion.convert.seckill.seckillactivity;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.common.util.collection.CollectionUtils;
import cn.zd.cer.framework.common.util.collection.MapUtils;
import cn.zd.cer.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityCreateReqVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityDetailRespVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityRespVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.activity.SeckillActivityUpdateReqVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.product.SeckillProductBaseVO;
import cn.zd.cer.module.promotion.controller.admin.seckill.vo.product.SeckillProductRespVO;
import cn.zd.cer.module.promotion.dal.dataobject.seckill.seckillactivity.SeckillActivityDO;
import cn.zd.cer.module.promotion.dal.dataobject.seckill.seckillactivity.SeckillProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 秒杀活动 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SeckillActivityConvert {

    SeckillActivityConvert INSTANCE = Mappers.getMapper(SeckillActivityConvert.class);

    SeckillActivityDO convert(SeckillActivityCreateReqVO bean);

    SeckillActivityDO convert(SeckillActivityUpdateReqVO bean);

    SeckillActivityRespVO convert(SeckillActivityDO bean);

    List<SeckillActivityRespVO> convertList(List<SeckillActivityDO> list);

    PageResult<SeckillActivityRespVO> convertPage(PageResult<SeckillActivityDO> page);

    default PageResult<SeckillActivityRespVO> convertPage(PageResult<SeckillActivityDO> page,
                                                          List<SeckillProductDO> seckillProducts,
                                                          List<ProductSpuRespDTO> spuList) {
        PageResult<SeckillActivityRespVO> pageResult = convertPage(page);
        // 拼接商品
        Map<Long, ProductSpuRespDTO> spuMap = CollectionUtils.convertMap(spuList, ProductSpuRespDTO::getId);
        pageResult.getList().forEach(item -> {
            item.setProducts(convertList2(seckillProducts));
            MapUtils.findAndThen(spuMap, item.getSpuId(),
                    spu -> item.setSpuName(spu.getName()).setPicUrl(spu.getPicUrl()));
        });
        return pageResult;
    }

    SeckillActivityDetailRespVO convert1(SeckillActivityDO activity);

    default SeckillActivityDetailRespVO convert(SeckillActivityDO activity, List<SeckillProductDO> products) {
        return convert1(activity).setProducts(convertList2(products));
    }

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "activityId", source = "activity.id"),
            @Mapping(target = "configIds", source = "activity.configIds"),
            @Mapping(target = "spuId", source = "activity.spuId"),
            @Mapping(target = "skuId", source = "product.skuId"),
            @Mapping(target = "seckillPrice", source = "product.seckillPrice"),
            @Mapping(target = "stock", source = "product.stock"),
            @Mapping(target = "activityStartTime", source = "activity.startTime"),
            @Mapping(target = "activityEndTime", source = "activity.endTime")
    })
    SeckillProductDO convert(SeckillActivityDO activity, SeckillProductBaseVO product);

    default List<SeckillProductDO> convertList(List<? extends SeckillProductBaseVO> products, SeckillActivityDO activity) {
        return CollectionUtils.convertList(products, item -> convert(activity, item).setActivityStatus(activity.getStatus()));
    }

    List<SeckillProductRespVO> convertList2(List<SeckillProductDO> list);

}
