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
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());

        setSubjectIds(studentDto, student);
        return studentDto;
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
        student.setGroup(groupMapper.groupById(requestDto.groupId()));

        setSubject(student, requestDto);
        return student;
    }
}
