package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IApiAssembler {
    IApiAssembler IMPL = Mappers.getMapper(IApiAssembler.class);


    ApiAO paramToAo(ApiAddParam apiAddParam);

    ApiAO paramToAo(ApiUpdateParam apiUpdateParam);

    @Mapping(target = "apiInputParams", expression = "java(apiAo.getParameterEntity().getInputParameterList())")
    @Mapping(target = "apiOutputParams", expression = "java(apiAo.getParameterEntity().getOutputParameterList())")
    ApiInfoDTO aoToDto(ApiAO apiAo);

    List<ApiDTO> voListToDtoList(List<ApiVO> apiList);
}
