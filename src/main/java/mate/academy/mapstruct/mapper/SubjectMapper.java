package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Objects;
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

    default List<Subject> getSubjectByIds(List<Long> ids) {
        return ids.stream()
                .filter(Objects::nonNull)
                .map(Subject::new)
                .toList();
    }

    default List<Long> getSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
