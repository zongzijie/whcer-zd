package cn.zd.cer.module.pay.convert.wallet;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import cn.zd.cer.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import cn.zd.cer.module.pay.service.wallet.bo.CreateWalletTransactionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<AppPayWalletTransactionRespVO> convertPage(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(CreateWalletTransactionBO bean);

}
