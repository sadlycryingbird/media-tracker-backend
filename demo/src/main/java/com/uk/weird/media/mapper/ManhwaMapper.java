package com.uk.weird.media.mapper;

import com.uk.weird.media.dto.ManhwaWriteDTO;
import com.uk.weird.media.dto.ManhwaReadDTO;
import com.uk.weird.media.entity.Manhwa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ManhwaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    Manhwa toEntity(ManhwaWriteDTO dto);

    @Mapping(target = "externalId", source = "externalId")
    @Mapping(target = "title", source = "media.title")
    @Mapping(target = "description", source = "media.description")
    @Mapping(target = "releaseYear", source = "media.releaseYear")
    @Mapping(target = "coverImageUrl", source = "media.coverImageUrl")
    @Mapping(target = "rating", source = "media.rating")
    @Mapping(target = "status", source = "media.status")
    @Mapping(target = "progress", source = "media.progress")
    ManhwaReadDTO toReadDTO(Manhwa manhwa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "media", ignore = true)
    void updateEntity(@MappingTarget Manhwa manhwa, ManhwaWriteDTO dto);
}

