package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public SubjectDto toDto(Subject subject) {
        if (subject == null) {
            return null;
        }

        return new SubjectDto(subject.getId(), subject.getName());

    }

    @Override
    public Subject toModel(CreateSubjectRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setName(requestDto.name());
        return subject;
    }
}
