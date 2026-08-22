package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    @Mapping(target = "id", ignore = true)
    Subject toModel(CreateSubjectRequestDto requestDto);

    default Long map(Subject subject) {
        return subject != null ? subject.getId() : null;
    }

    default Subject map(Long subjectId) {
        return subjectId != null ? new Subject(subjectId) : null;
    }

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects != null
                ? subjects.stream().map(this::map).toList()
                : null;
    }

    default List<Subject> mapIdsToSubjects(List<Long> subjectIds) {
        return subjectIds != null
                ? subjectIds.stream().map(this::map).toList()
                : null;
    }
}
