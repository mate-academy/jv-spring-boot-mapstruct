package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    SubjectDto toDto(Subject subject);

    @Mapping(source = "name", target = "name")
    Subject toModel(CreateSubjectRequestDto requestDto);
}
