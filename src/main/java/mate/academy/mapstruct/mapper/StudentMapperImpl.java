package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        if (student.getGroup() != null) {
            studentDto.setGroupId(student.getGroup().getId());
        }
        setSubjectIds(studentDto, student);
        return studentDto;
    }

    private void setSubjectIds(StudentDto studentDto, Student student) {
        List<Long> ids = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(ids);
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        if (student == null) {
            return null;
        }

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
        setSubjects(requestDto.subjects(), student);
        return student;
    }

    private void setSubjects(List<Long> ids, Student student) {
        List<Subject> subjects = ids.stream()
                .map(Subject::new).toList();
        student.setSubjects(subjects);
    }
}
