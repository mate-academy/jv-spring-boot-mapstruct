package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class})
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectListFromIds")
    default List<Subject> subjectListFromIds(List<Long> idsList) {
        if (idsList == null || idsList.isEmpty()) {
            return new ArrayList<>();
        }
        return idsList.stream()
                .map(Subject::new)
                .toList();
    }

    @Named("subjectListFromIds")
    default List<Long> subjectIdsFromSubjects(List<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return new ArrayList<>();
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
