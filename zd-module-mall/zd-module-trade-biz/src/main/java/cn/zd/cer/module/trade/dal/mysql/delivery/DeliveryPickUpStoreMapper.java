package cn.zd.cer.module.trade.dal.mysql.delivery;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import cn.zd.cer.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zd.cer.module.trade.controller.admin.delivery.vo.pickup.DeliveryPickUpStorePageReqVO;
import cn.zd.cer.module.trade.dal.dataobject.delivery.DeliveryPickUpStoreDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryPickUpStoreMapper extends BaseMapperX<DeliveryPickUpStoreDO> {

    default PageResult<DeliveryPickUpStoreDO> selectPage(DeliveryPickUpStorePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeliveryPickUpStoreDO>()
                .likeIfPresent(DeliveryPickUpStoreDO::getName, reqVO.getName())
                .eqIfPresent(DeliveryPickUpStoreDO::getPhone, reqVO.getPhone())
                .eqIfPresent(DeliveryPickUpStoreDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(DeliveryPickUpStoreDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeliveryPickUpStoreDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeliveryPickUpStoreDO::getId));
    }

    default List<DeliveryPickUpStoreDO> selectListByStatus(Integer status) {
        return selectList(DeliveryPickUpStoreDO::getStatus, status);
    }

}




