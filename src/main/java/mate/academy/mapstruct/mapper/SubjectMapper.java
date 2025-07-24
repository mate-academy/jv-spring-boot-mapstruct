package mate.academy.mapstruct.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectById")
    default Subject subjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }

    @Named("subjectsByIds")
    default List<Subject> subjectsByIds(List<Long> ids) {
        return Optional.ofNullable(ids)
                .orElse(Collections.emptyList())
                .stream().map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("subjectsToIds")
    default List<Long> subjectsToIds(List<Subject> subjects) {
        return Optional.ofNullable(subjects)
                .orElse(Collections.emptyList())
                .stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
