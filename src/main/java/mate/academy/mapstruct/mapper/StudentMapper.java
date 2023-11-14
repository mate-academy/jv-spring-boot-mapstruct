package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group", qualifiedByName = "getIdFromGroup")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getIdsFromSubjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "getIdFromGroup")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsByIds")
    Student toModel(CreateStudentRequestDto requestDto);
}
