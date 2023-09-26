package cn.zd.cer.module.infra.convert.file;

import cn.zd.cer.framework.common.pojo.PageResult;
import cn.zd.cer.module.infra.controller.admin.file.vo.file.FileRespVO;
import cn.zd.cer.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}
