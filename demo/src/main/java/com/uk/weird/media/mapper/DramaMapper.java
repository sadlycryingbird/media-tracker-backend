package com.uk.weird.media.mapper;

import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.entity.Drama;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = MediaMapper.class)
public interface DramaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    Drama toEntity(DramaWriteDTO dto);

    @Mapping(target = "media", source = "media")
    DramaReadDTO toReadDTO(Drama drama);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    void updateEntity(@MappingTarget Drama drama, DramaWriteDTO dto);
}
