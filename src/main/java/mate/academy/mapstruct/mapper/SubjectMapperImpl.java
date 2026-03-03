package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;

public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public SubjectDto toDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName());
    }

    @Override
    public Subject toModel(CreateSubjectRequestDto requestDto) {
        Subject subject = new Subject();
        subject.setName(requestDto.name());
        return subject;
    }
}
