package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
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
        studentDto.setSubjectIds(student.getSubjects().stream().map(p -> p.getId()).toList());
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setGroupId(student.getGroup().getId());
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student) {
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
        final Group group = new Group(requestDto.groupId());
        List<Subject> subjects = new ArrayList<>();
        for (Long l:requestDto.subjects()) {
            Subject subject = new Subject();
            subject.setId(l);
            subjects.add(subject);
        }

        student.setName(requestDto.name());
        student.setEmail(requestDto.email());
        student.setGroup(group);
        student.setSubjects(subjects);
        return student;
    }
}
