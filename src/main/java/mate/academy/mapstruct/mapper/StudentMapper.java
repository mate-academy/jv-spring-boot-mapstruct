package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;

public interface StudentMapper {
    StudentDto toDto(Student student);

    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    Student toModel(CreateStudentRequestDto requestDto);
}
