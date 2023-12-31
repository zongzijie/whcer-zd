package cn.zd.cer.module.product.convert.spu;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.common.util.collection.CollectionUtils;
import cn.zd.cer.framework.dict.core.util.DictFrameworkUtils;
import cn.zd.cer.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.zd.cer.module.product.controller.admin.spu.vo.*;
import cn.zd.cer.module.product.controller.app.spu.vo.AppProductSpuDetailRespVO;
import cn.zd.cer.module.product.controller.app.spu.vo.AppProductSpuPageReqVO;
import cn.zd.cer.module.product.controller.app.spu.vo.AppProductSpuPageRespVO;
import cn.zd.cer.module.product.convert.sku.ProductSkuConvert;
import cn.zd.cer.module.product.dal.dataobject.sku.ProductSkuDO;
import cn.zd.cer.module.product.dal.dataobject.spu.ProductSpuDO;
import cn.zd.cer.module.product.enums.DictTypeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.ObjectUtil.defaultIfNull;
import static cn.zd.cer.framework.common.util.collection.CollectionUtils.convertMultiMap;

/**
 * 商品 SPU Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuDO convert(ProductSpuCreateReqVO bean);

    ProductSpuDO convert(ProductSpuUpdateReqVO bean);

    List<ProductSpuDO> convertList(List<ProductSpuDO> list);

    PageResult<ProductSpuRespVO> convertPage(PageResult<ProductSpuDO> page);

    ProductSpuPageReqVO convert(AppProductSpuPageReqVO bean);

    List<ProductSpuRespDTO> convertList2(List<ProductSpuDO> list);

    List<ProductSpuSimpleRespVO> convertList02(List<ProductSpuDO> list);

    @Mapping(target = "price", expression = "java(spu.getPrice() / 100)")
    @Mapping(target = "marketPrice", expression = "java(spu.getMarketPrice() / 100)")
    @Mapping(target = "costPrice", expression = "java(spu.getCostPrice() / 100)")
    ProductSpuExcelVO convert(ProductSpuDO spu);

    default List<ProductSpuExcelVO> convertList03(List<ProductSpuDO> list) {
        List<ProductSpuExcelVO> spuExcelVOs = new ArrayList<>();
        list.forEach(spu -> {
            ProductSpuExcelVO spuExcelVO = convert(spu);
            spuExcelVOs.add(spuExcelVO);
        });
        return spuExcelVOs;
    }

    ProductSpuDetailRespVO convert03(ProductSpuDO spu);

    ProductSpuRespDTO convert02(ProductSpuDO bean);

    // ========== 用户 App 相关 ==========

    PageResult<AppProductSpuPageRespVO> convertPageForGetSpuPage(PageResult<ProductSpuDO> page);

    default List<AppProductSpuPageRespVO> convertListForGetSpuList(List<ProductSpuDO> list) {
        // 处理虚拟销量
        list.forEach(spu -> spu.setSalesCount(spu.getSalesCount() + spu.getVirtualSalesCount()));
        // 处理 VO 字段
        List<AppProductSpuPageRespVO> voList = convertListForGetSpuList0(list);
        for (int i = 0; i < list.size(); i++) {
            ProductSpuDO spu = list.get(i);
            AppProductSpuPageRespVO spuVO = voList.get(i);
            spuVO.setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, spu.getUnit()));
            // 计算 vip 价格 TODO 芋艿：临时的逻辑，等 vip 支持后
            spuVO.setVipPrice((int) (spuVO.getPrice() * 0.9));
        }
        return voList;
    }

    @Named("convertListForGetSpuList0")
    List<AppProductSpuPageRespVO> convertListForGetSpuList0(List<ProductSpuDO> list);

    default AppProductSpuDetailRespVO convertForGetSpuDetail(ProductSpuDO spu, List<ProductSkuDO> skus) {
        // 处理 SPU
        AppProductSpuDetailRespVO spuVO = convertForGetSpuDetail(spu)
                .setSalesCount(spu.getSalesCount() + defaultIfNull(spu.getVirtualSalesCount(), 0))
                .setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, spu.getUnit()));
        // 处理 SKU
        spuVO.setSkus(convertListForGetSpuDetail(skus));
        // 计算 vip 价格 TODO 芋艿：临时的逻辑，等 vip 支持后
        if (true) {
            spuVO.setVipPrice((int) (spuVO.getPrice() * 0.9));
            spuVO.getSkus().forEach(sku -> sku.setVipPrice((int) (sku.getPrice() * 0.9)));
        }
        return spuVO;
    }

    AppProductSpuDetailRespVO convertForGetSpuDetail(ProductSpuDO spu);

    List<AppProductSpuDetailRespVO.Sku> convertListForGetSpuDetail(List<ProductSkuDO> skus);

    default ProductSpuDetailRespVO convertForSpuDetailRespVO(ProductSpuDO spu, List<ProductSkuDO> skus) {
        ProductSpuDetailRespVO detailRespVO = convert03(spu);
        detailRespVO.setSkus(ProductSkuConvert.INSTANCE.convertList(skus));
        return detailRespVO;
    }

    default List<ProductSpuDetailRespVO> convertForSpuDetailRespListVO(List<ProductSpuDO> spus, List<ProductSkuDO> skus) {
        Map<Long, List<ProductSkuDO>> skuMultiMap = convertMultiMap(skus, ProductSkuDO::getSpuId);
        return CollectionUtils.convertList(spus, spu -> convert03(spu)
                .setSkus(ProductSkuConvert.INSTANCE.convertList(skuMultiMap.get(spu.getId()))));
    }

}
