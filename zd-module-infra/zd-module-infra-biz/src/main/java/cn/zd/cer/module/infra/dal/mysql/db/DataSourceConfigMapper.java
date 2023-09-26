package cn.zd.cer.module.infra.dal.mysql.db;

import cn.zd.cer.framework.mybatis.core.mapper.BaseMapperX;
import cn.zd.cer.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
