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

    @Named("subjectIds")
    default List<Subject> subjectById(List<Long> subjectId) {
        return subjectId.stream()
                .map(Subject::new)
                .toList();

    }

    @Named("subjectId2")
    default List<Long> subjectId2(List<Subject> subjectId) {
        return subjectId.stream()
                .map(Subject::getId)
                .toList();
    }
}
