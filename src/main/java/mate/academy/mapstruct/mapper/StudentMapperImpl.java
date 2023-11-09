package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;

public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(student.getEmail());
        studentDto.setName(student.getName());
        if (student.getGroup() != null) {
            studentDto.setGroupId(student.getGroup().getId());
        }
        setSubjectIds(studentDto, student);
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentWithoutSubjectsDto studentDto = new StudentWithoutSubjectsDto();
        studentDto.setEmail(student.getEmail());
        studentDto.setName(student.getName());
        if (student.getGroup() != null) {
            studentDto.setGroupId(student.getGroup().getId());
        }
        return studentDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Student student = new Student();
        student.setGroup(new Group(requestDto.groupId()));
        student.setEmail(requestDto.email());
        student.setName(requestDto.name());
        setSubjects(student, requestDto);
        return student;
    }
}
