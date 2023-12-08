package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("getSubjectsByIds")
    default List<Subject> getSubjectsByIds(List<Long> longList) {
        return longList.stream().map(Subject::new).toList();
    }

    @Named("getIdsFromSubjects")
    default List<Long> getIdsFromSubjects(List<Subject> subjects) {
        return subjects.stream().map(subject -> subject.getId()).toList();
    }
}
