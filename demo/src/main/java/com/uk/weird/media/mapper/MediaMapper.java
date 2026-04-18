package com.uk.weird.media.mapper;

import com.uk.weird.media.dto.MediaWriteDTO;
import com.uk.weird.media.dto.MediaReadDTO;
import com.uk.weird.media.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Media toEntity(MediaWriteDTO dto);

    @Mapping(target = "createdAt", expression = "java(media.getCreatedAt().toString())")
    @Mapping(target = "updatedAt", expression = "java(media.getUpdatedAt().toString())")
    MediaReadDTO toReadDTO(Media media);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Media media, MediaWriteDTO dto);
}
