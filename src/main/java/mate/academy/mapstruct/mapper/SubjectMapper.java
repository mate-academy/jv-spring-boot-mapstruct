package mate.academy.mapstruct.mapper;

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

    @Mapping(target = "id", source = "subject.id")
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("getSubjectById")
    default Subject getSubjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }
}
