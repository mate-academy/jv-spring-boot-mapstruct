package mate.academy.mapstruct.mapper;

import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentMapperImpl implements StudentMapper {
    private final SubjectMapper subjectMapper;

    @Override
    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setGroupId(student.getGroup().getId());
        if (student.getSubjects() != null) {
            studentDto.setSubjectIds(student.getSubjects().stream().map(Subject::getId).toList());
        }
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        StudentWithoutSubjectsDto studentWithoutSubjectsDto = new StudentWithoutSubjectsDto();
        studentWithoutSubjectsDto.setId(student.getId());
        studentWithoutSubjectsDto.setName(student.getName());
        studentWithoutSubjectsDto.setEmail(student.getEmail());
        studentWithoutSubjectsDto.setGroupId(student.getGroup().getId());
        return studentWithoutSubjectsDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Student student = new Student();
        student.setName(requestDto.name());
        student.setEmail(requestDto.email());
        student.setGroup(new Group(requestDto.groupId()));
        student.setSubjects(requestDto.subjects().stream()
                .map(subjectMapper::subjectById).toList());
        return student;
    }
}
