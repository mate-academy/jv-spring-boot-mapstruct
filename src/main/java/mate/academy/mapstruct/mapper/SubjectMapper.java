package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Named("idsBySubjects")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("subjectsById")
    default List<Subject> mapIdsToSubjects(List<Long> ids) {
        return ids.stream()
                .map(this::subjectById)
                .collect(Collectors.toList());
    }

    @Named("subjectById")
    default Subject subjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }
}
