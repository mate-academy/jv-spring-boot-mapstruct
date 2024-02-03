package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group", qualifiedByName = "getGroupId")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getSubjectIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "getGroupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsByIds")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);
}