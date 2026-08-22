package mate.academy.mapstruct.mapper;

import java.util.Collections;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    default StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setGroupId(student.getGroup() != null ? student.getGroup().getId() : null);
        studentDto.setSubjectIds(student.getSubjects() != null
                ? student.getSubjects().stream().map(Subject::getId).collect(Collectors.toList())
                : Collections.emptyList());
        return studentDto;
    }

    default StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        StudentWithoutSubjectsDto studentWithoutSubjectsDto = new StudentWithoutSubjectsDto();
        studentWithoutSubjectsDto.setGroupId(student.getGroup().getId());
        studentWithoutSubjectsDto.setId(student.getId());
        studentWithoutSubjectsDto.setEmail(student.getEmail());
        studentWithoutSubjectsDto.setName(student.getName());
        return studentWithoutSubjectsDto;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);
}
