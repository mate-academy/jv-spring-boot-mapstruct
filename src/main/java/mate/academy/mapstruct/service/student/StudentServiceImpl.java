package mate.academy.mapstruct.service.student;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.model.Student;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.exception.EntityNotFoundException;
import mate.academy.mapstruct.mapper.StudentMapper;
import mate.academy.mapstruct.repository.student.StudentRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto save(CreateStudentRequestDto requestDto) {
        Student student = studentMapper.toModel(requestDto);
        student.setSocialSecurityNumber("abc " + new Random().nextInt(1000));
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public List<StudentWithoutSubjectsDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toEmployeeWithoutSubjectsDto)
                .toList();
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find employee by id" + id)
        );
        return studentMapper.toDto(student);
    }
}
