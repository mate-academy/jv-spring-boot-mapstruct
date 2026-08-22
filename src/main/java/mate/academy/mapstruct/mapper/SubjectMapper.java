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

    @Named("subjectsToIds")
    default List<Long> subjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("idsToSubjects")
    default List<Subject> idsToSubjects(List<Long> subjectsIds) {
        if (subjectsIds == null) {
            return null;
        }
        return subjectsIds.stream()
                .map(Subject::new)
                .toList();
    }
}
