package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectIdsToSubjects")
    default List<Subject> subjectIdsToSubjects(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
