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

    @Named("fromSubjectsToSubjectsIds")
    default List<Long> subjectsIds(List<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("fromSubjectsIdsToSubjects")
    default List<Subject> subjectList(List<Long> subjectIds) {
        if (subjectIds == null || subjectIds.isEmpty()) {
            return null;
        }
        return subjectIds
                .stream()
                .map(Subject::new)
                .toList();
    }
}
