package mate.academy.mapstruct.mapper;

import lombok.AllArgsConstructor;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;

@AllArgsConstructor
public class StudentMapperImpl implements StudentMapper {
    private final GroupMapper groupMapper;

    @Override
    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        final StudentDto dto = new StudentDto();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setGroup(groupMapper.groupById(dto.getId()));
        setSubjectIds(dto, student);
        return dto;
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        if (student == null) {
            return null;
        }
        final StudentWithoutSubjectsDto dto = new StudentWithoutSubjectsDto();
        student.setId(student.getId());
        student.setName(student.getName());
        student.setEmail(student.getEmail());
        student.setGroup(groupMapper.groupById(student.getId()));

        return dto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        final Student student = new Student();
        student.setName(requestDto.name());
        student.setEmail(requestDto.email());
        student.setGroup(groupMapper.groupById(requestDto.groupId()));
        getSubjects(requestDto, student);
        return student;
    }
}
