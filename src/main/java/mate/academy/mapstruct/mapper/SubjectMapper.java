package mate.academy.mapstruct.mapper;

import org.mapstruct.Mapper;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);
}
