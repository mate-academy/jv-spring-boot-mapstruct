package mate.academy.mapstruct.mapper.impl;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.mapper.GroupMapper;
import mate.academy.mapstruct.mapper.StudentMapper;
import mate.academy.mapstruct.model.Student;

public class StudentMapperImpl implements StudentMapper {
    private final GroupMapper groupMapper;

    public StudentMapperImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setName(student.getName());
        studentDto.setGroupId(student.getGroup() != null
                ? student.getGroup().getId() : null);
        setSubjectIds(studentDto, student);
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        StudentWithoutSubjectsDto studentDto = new StudentWithoutSubjectsDto();
        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setName(student.getName());
        studentDto.setGroupId(student.getGroup() != null
                ? student.getGroup().getId() : null);
        return studentDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        Student student = new Student();
        student.setName(requestDto.name());
        student.setEmail(requestDto.email());
        student.setGroup(groupMapper.groupById(requestDto.groupId()));

        setSubjects(student, requestDto);

        return student;
    }

}
