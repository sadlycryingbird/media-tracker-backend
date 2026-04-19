package com.uk.weird.media.mapper;

import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.entity.Drama;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DramaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    Drama toEntity(DramaWriteDTO dto);

    @Mapping(target = "externalId", source = "externalId")
    @Mapping(target = "title", source = "media.title")
    @Mapping(target = "description", source = "media.description")
    @Mapping(target = "releaseYear", source = "media.releaseYear")
    @Mapping(target = "coverImageUrl", source = "media.coverImageUrl")
    @Mapping(target = "rating", source = "media.rating")
    @Mapping(target = "status", source = "media.status")
    @Mapping(target = "progress", source = "media.progress")
    DramaReadDTO toReadDTO(Drama drama);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    void updateEntity(@MappingTarget Drama drama, DramaWriteDTO dto);
}

