package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GenericMapperConfig.class)
public interface SubjectMapper {
    @Mapping(target = "id", ignore = true)
    Subject toModel(CreateSubjectRequestDto requestDto);

    SubjectDto toDto(Subject subject);
}

