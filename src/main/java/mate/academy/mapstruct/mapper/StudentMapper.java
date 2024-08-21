package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "student.group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "student.group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group", source = "groupId")
    Student toModel(CreateStudentRequestDto requestDto);
}
