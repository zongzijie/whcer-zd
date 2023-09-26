package cn.zd.cer.module.trade.api.brokerage;

import cn.zd.cer.module.trade.api.brokerage.dto.BrokerageUserDTO;
import cn.zd.cer.module.trade.convert.brokerage.user.BrokerageUserConvert;
import cn.zd.cer.module.trade.service.brokerage.user.BrokerageUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 分销 API 接口实现类
 *
 * @author owen
 */
@Service
@Validated
public class BrokerageApiImpl implements BrokerageApi {

    @Resource
    private BrokerageUserService brokerageUserService;

    @Override
    public BrokerageUserDTO getBrokerageUser(Long userId) {
        return BrokerageUserConvert.INSTANCE.convertDTO(brokerageUserService.getBrokerageUser(userId));
    }

    @Override
    public boolean bindUser(Long userId, Long bindUserId, Boolean isNewUser) {
        return brokerageUserService.bindBrokerageUser(userId, bindUserId, isNewUser);
    }

}
