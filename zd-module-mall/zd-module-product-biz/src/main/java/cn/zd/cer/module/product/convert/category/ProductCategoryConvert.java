package cn.zd.cer.module.product.convert.category;

import cn.zd.cer.module.product.controller.admin.category.vo.ProductCategoryCreateReqVO;
import cn.zd.cer.module.product.controller.admin.category.vo.ProductCategoryRespVO;
import cn.zd.cer.module.product.controller.admin.category.vo.ProductCategoryUpdateReqVO;
import cn.zd.cer.module.product.controller.app.category.vo.AppCategoryRespVO;
import cn.zd.cer.module.product.dal.dataobject.category.ProductCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品分类 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategoryDO convert(ProductCategoryCreateReqVO bean);

    ProductCategoryDO convert(ProductCategoryUpdateReqVO bean);

    ProductCategoryRespVO convert(ProductCategoryDO bean);

    List<ProductCategoryRespVO> convertList(List<ProductCategoryDO> list);

    List<AppCategoryRespVO> convertList03(List<ProductCategoryDO> list);
}
