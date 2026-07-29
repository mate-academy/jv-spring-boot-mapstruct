package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.GlobalMapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = GlobalMapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectById")
    default Subject subjectById(Long id) {
        return (id == null) ? null : new Subject(id);
    }
}
