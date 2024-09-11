package mate.academy.mapstruct.service.student;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.mapper.StudentMapper;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import mate.academy.mapstruct.repository.student.StudentRepository;
import mate.academy.mapstruct.repository.subject.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              SubjectRepository subjectRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public StudentDto save(CreateStudentRequestDto requestDto) {
        Student student = studentMapper.toModel(requestDto);
        List<Subject> subjects = subjectRepository.findAllById(requestDto.getSubjects());
        student.setSubjects(subjects);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }
}
