package mate.academy.mapstruct.service.student;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;

public interface StudentService {
    StudentDto save(CreateStudentRequestDto requestDto);
    StudentDto findById(Long id);
}
