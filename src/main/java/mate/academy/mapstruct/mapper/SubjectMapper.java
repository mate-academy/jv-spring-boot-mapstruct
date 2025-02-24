package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
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

    @Named("subjectByIds")
    default List<Subject> mapSubjectIdsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return new ArrayList<>();
        }
        return subjectIds.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .toList();
    }
}
