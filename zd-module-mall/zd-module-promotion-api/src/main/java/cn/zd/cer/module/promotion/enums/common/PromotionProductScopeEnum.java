package cn.zd.cer.module.promotion.enums.common;

import cn.zd.cer.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 营销的商品范围枚举
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum PromotionProductScopeEnum implements IntArrayValuable {

    ALL(1, "通用卷"), // 全部商品
    SPU(2, "商品卷"), // 指定商品
    CATEGORY(3, "品类卷"), // 指定商品
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PromotionProductScopeEnum::getScope).toArray();

    /**
     * 范围值
     */
    private final Integer scope;
    /**
     * 范围名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
