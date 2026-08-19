package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("setSubjectIds")
    default List<Long> setSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("setSubjectsById")
    default List<Subject> setSubjectsById(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
