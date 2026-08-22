package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    @Mapping(target = "id", ignore = true)
    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectsById")
    default List<Long> subjectsById(List<Subject> subjects) {
        return subjects == null ? null : subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("subjectIdToSubject")
    default List<Subject> subjectIdToSubject(List<Long> subjects) {
        return subjects == null ? null : subjects.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .collect(Collectors.toList());
    }
}
