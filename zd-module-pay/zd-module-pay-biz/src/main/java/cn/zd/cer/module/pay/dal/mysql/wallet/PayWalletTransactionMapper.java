package cn.zd.cer.module.pay.dal.mysql.wallet;


import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import cn.zd.cer.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zd.cer.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import cn.zd.cer.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

@Mapper
public interface PayWalletTransactionMapper extends BaseMapperX<PayWalletTransactionDO> {

    default PageResult<PayWalletTransactionDO> selectPage(Long walletId,
                                                          AppPayWalletTransactionPageReqVO pageReqVO) {
        LambdaQueryWrapperX<PayWalletTransactionDO> query = new LambdaQueryWrapperX<PayWalletTransactionDO>()
                .eq(PayWalletTransactionDO::getWalletId, walletId);
        if (Objects.equals(pageReqVO.getType(), AppPayWalletTransactionPageReqVO.TYPE_INCOME)) {
            query.gt(PayWalletTransactionDO::getPrice, 0);
        } else if (Objects.equals(pageReqVO.getType(), AppPayWalletTransactionPageReqVO.TYPE_EXPENSE)) {
            query.lt(PayWalletTransactionDO::getPrice, 0);
        }
        query.orderByDesc(PayWalletTransactionDO::getId);
        return selectPage(pageReqVO, query);
    }

    default PayWalletTransactionDO selectByNo(String no) {
        return selectOne(PayWalletTransactionDO::getNo, no);
    }

    default PayWalletTransactionDO selectByBiz(String bizId, Integer bizType) {
        return selectOne(PayWalletTransactionDO::getBizId, bizId,
                PayWalletTransactionDO::getBizType, bizType);
    }

}




