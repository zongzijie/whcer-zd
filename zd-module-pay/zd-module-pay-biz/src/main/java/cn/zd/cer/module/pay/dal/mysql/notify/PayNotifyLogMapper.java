package cn.zd.cer.module.pay.dal.mysql.notify;

import cn.zd.cer.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
