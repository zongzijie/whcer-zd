package cn.zd.cer.module.trade.dal.mysql.order;

import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import cn.zd.cer.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zd.cer.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface TradeOrderItemMapper extends BaseMapperX<TradeOrderItemDO> {

    default int updateAfterSaleStatus(Long id, Integer oldAfterSaleStatus, Integer newAfterSaleStatus,
                                      Long afterSaleId) {
        return update(new TradeOrderItemDO().setAfterSaleStatus(newAfterSaleStatus).setAfterSaleId(afterSaleId),
                new LambdaUpdateWrapper<>(new TradeOrderItemDO().setId(id).setAfterSaleStatus(oldAfterSaleStatus)));
    }

    default List<TradeOrderItemDO> selectListByOrderId(Long orderId) {
        return selectList(TradeOrderItemDO::getOrderId, orderId);
    }

    default List<TradeOrderItemDO> selectListByOrderId(Collection<Long> orderIds) {
        return selectList(TradeOrderItemDO::getOrderId, orderIds);
    }

    default List<TradeOrderItemDO> selectListByOrderIdAnSkuId(Collection<Long> orderIds, Collection<Long> skuIds) {
        return selectList(new LambdaQueryWrapperX<TradeOrderItemDO>()
                .in(TradeOrderItemDO::getOrderId, orderIds)
                .eq(TradeOrderItemDO::getSkuId, skuIds));
    }

    default TradeOrderItemDO selectByIdAndUserId(Long orderItemId, Long loginUserId) {
        return selectOne(new LambdaQueryWrapperX<TradeOrderItemDO>()
                .eq(TradeOrderItemDO::getId, orderItemId)
                .eq(TradeOrderItemDO::getUserId, loginUserId));
    }

}
