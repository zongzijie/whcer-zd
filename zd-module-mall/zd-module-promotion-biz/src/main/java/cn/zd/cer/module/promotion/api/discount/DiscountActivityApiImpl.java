package cn.zd.cer.module.promotion.api.discount;

import cn.zd.cer.module.promotion.api.discount.dto.DiscountProductRespDTO;
import cn.zd.cer.module.promotion.convert.discount.DiscountActivityConvert;
import cn.zd.cer.module.promotion.service.discount.DiscountActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 限时折扣 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class DiscountActivityApiImpl implements DiscountActivityApi {

    @Resource
    private DiscountActivityService discountActivityService;

    @Override
    public List<DiscountProductRespDTO> getMatchDiscountProductList(Collection<Long> skuIds) {
        return DiscountActivityConvert.INSTANCE.convertList02(discountActivityService.getMatchDiscountProductList(skuIds));
    }

}
