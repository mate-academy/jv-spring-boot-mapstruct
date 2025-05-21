package mate.academy.mapstruct.mapper.impl;

import javax.annotation.processing.Generated;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.mapper.SubjectMapper;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T14:42:44+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectDto toDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        if ( subject.getId() != null ) {
            id = subject.getId();
        }
        if ( subject.getName() != null ) {
            name = subject.getName();
        }

        SubjectDto subjectDto = new SubjectDto( id, name );

        return subjectDto;
    }

    @Override
    public Subject toModel(CreateSubjectRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        if ( requestDto.name() != null ) {
            subject.setName( requestDto.name() );
        }

        return subject;
    }
}
