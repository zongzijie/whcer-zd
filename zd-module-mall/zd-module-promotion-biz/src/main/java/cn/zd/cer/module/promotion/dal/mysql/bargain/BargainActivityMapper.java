package cn.zd.cer.module.promotion.dal.mysql.bargain;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import cn.zd.cer.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zd.cer.module.promotion.controller.admin.bargain.vo.BargainActivityPageReqVO;
import cn.zd.cer.module.promotion.dal.dataobject.bargain.BargainActivityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 砍价活动 Mapper
 *
 * @author HUIHUI
 */
@Mapper
public interface BargainActivityMapper extends BaseMapperX<BargainActivityDO> {

    default PageResult<BargainActivityDO> selectPage(BargainActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BargainActivityDO>()
                .likeIfPresent(BargainActivityDO::getName, reqVO.getName())
                .eqIfPresent(BargainActivityDO::getStatus, reqVO.getStatus())
                .orderByDesc(BargainActivityDO::getId));
    }

    default List<BargainActivityDO> selectListByStatus(Integer status) {
        return selectList(BargainActivityDO::getStatus, status);
    }

}
