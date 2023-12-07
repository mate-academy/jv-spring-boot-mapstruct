package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;

public class StudentMapperImpl implements StudentMapper {
    private final GroupMapper groupMapper;

    public StudentMapperImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(student.getEmail());
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setGroupId(studentDto.getGroupId());
        setSubjectIds(studentDto, student);
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentWithoutSubjectsDto studentWithoutSubjectsDto = new StudentWithoutSubjectsDto();
        studentWithoutSubjectsDto.setEmail(student.getEmail());
        studentWithoutSubjectsDto.setName(student.getName());
        studentWithoutSubjectsDto.setId(student.getId());
        return studentWithoutSubjectsDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Student student = new Student();
        student.setEmail(requestDto.email());
        student.setName(requestDto.name());
        student.setGroup(groupMapper.groupById(requestDto.groupId()));
        setSubjects(student, requestDto);
        return student;
    }
}
