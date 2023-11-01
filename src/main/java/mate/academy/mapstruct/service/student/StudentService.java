package mate.academy.mapstruct.service.student;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;

public interface StudentService {
    StudentDto save(CreateStudentRequestDto requestDto);

    List<StudentWithoutSubjectsDto> findAll();

    StudentDto findById(Long id);
}
