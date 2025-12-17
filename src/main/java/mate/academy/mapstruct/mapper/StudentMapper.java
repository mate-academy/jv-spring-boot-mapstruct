package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MainMapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "idFromSubject")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectById")
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);
}
