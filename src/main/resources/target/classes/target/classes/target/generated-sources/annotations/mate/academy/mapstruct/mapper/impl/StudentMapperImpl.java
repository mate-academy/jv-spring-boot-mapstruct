package mate.academy.mapstruct.mapper.impl;

import java.util.List;
import javax.annotation.processing.Generated;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.mapper.StudentMapper;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T14:44:40+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        if ( student.getId() != null ) {
            studentDto.setId( student.getId() );
        }
        if ( student.getName() != null ) {
            studentDto.setName( student.getName() );
        }
        if ( student.getEmail() != null ) {
            studentDto.setEmail( student.getEmail() );
        }

        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentWithoutSubjectsDto studentWithoutSubjectsDto = new StudentWithoutSubjectsDto();

        if ( student.getId() != null ) {
            studentWithoutSubjectsDto.setId( student.getId() );
        }
        if ( student.getName() != null ) {
            studentWithoutSubjectsDto.setName( student.getName() );
        }
        if ( student.getEmail() != null ) {
            studentWithoutSubjectsDto.setEmail( student.getEmail() );
        }

        return studentWithoutSubjectsDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Student student = new Student();

        List<Subject> list = map( requestDto.subjects() );
        if ( list != null ) {
            student.setSubjects( list );
        }
        if ( requestDto.name() != null ) {
            student.setName( requestDto.name() );
        }
        if ( requestDto.email() != null ) {
            student.setEmail( requestDto.email() );
        }

        return student;
    }
}
