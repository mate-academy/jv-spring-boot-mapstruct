package mate.academy.mapstruct.mapper;

import java.util.List;
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

    @Named("subjectsByIds")
    default List<Subject> subjectsByIds(List<Long> values) {
        if (values == null) {
            return null;
        }
        return values.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("idsBySubjects")
    default List<Long> idsBySubjects(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
