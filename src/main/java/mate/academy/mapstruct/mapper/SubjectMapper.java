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

    @Named("getSubjectsByIds")
    default List<Subject> getSubjectsByIds(List<Long> ids) {
        return ids.stream().map(Subject::new).toList();
    }

    @Named("getIdsBySubjects")
    default List<Long> getIdsBySubjects(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).toList();
    }
}
