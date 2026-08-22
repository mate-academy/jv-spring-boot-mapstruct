package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapper {
    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto dto);

    List<SubjectDto> toDtoList(List<Subject> subjects);
}
