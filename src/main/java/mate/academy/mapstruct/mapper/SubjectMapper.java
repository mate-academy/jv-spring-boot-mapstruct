package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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

    @Named("subjectById")
    default Subject subjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }

    @Named("idBySubject")
    default Long idBySubject(Subject subject) {
        return subject.getId();
    }

    @Named("subjectListByIds")
    default List<Subject> subjectListByIds(List<Long> ids) {
        return Optional.ofNullable(ids)
                .stream()
                .flatMap(List::stream)
                .map(Subject::new)
                .toList();
    }

    @Named("idListBySubject")
    default List<Long> idListBySubject(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
