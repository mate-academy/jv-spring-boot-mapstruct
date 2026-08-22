package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "subject.id", target = "id")
    SubjectDto toDto(Subject subject);

    @Mapping(source = "name", target = "name")
    Subject toModel(CreateSubjectRequestDto requestDto);
}
