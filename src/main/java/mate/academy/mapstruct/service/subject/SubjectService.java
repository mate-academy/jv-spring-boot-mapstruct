package mate.academy.mapstruct.service.subject;

import java.util.List;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;

public interface SubjectService {
    SubjectDto save(CreateSubjectRequestDto requestDto);

    List<SubjectDto> findAll();
}
