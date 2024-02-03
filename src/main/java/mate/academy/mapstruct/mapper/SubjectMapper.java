package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    @Mapping(target = "id", ignore = true)
    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("getSubjectId")
    default Long getSubjectId(Subject subject) {
        return subject.getId();
    }

    @Named("getSubjectIds")
    default List<Long> getSubjectIds(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).collect(Collectors.toList());
    }

    @Named("getSubjectsByIds")
    default List<Subject> getSubjectsByIds(List<Long> subjects) {
        return subjects.stream().map(Subject::new).collect(Collectors.toList());
    }
}