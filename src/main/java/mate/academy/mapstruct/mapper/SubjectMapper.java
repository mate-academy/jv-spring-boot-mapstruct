package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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

    @Named("subjectByIds")
    default Subject subjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }

    @Named("subjectsByIds")
    default List<Subject> subjectsByIds(List<Long> id) {
        return id.stream()
                .map(this::subjectById)
                .toList();
    }

    @Named("idBySubject")
    default Long idBySubject(Subject subject) {
        return Optional.ofNullable(subject)
                .map(Subject::getId)
                .orElse(null);
    }

    @Named("idsBySubjects")
    default List<Long> idsBySubjects(List<Subject> subjects) {
        return subjects.stream()
                .map(this::idBySubject)
                .toList();
    }
}
