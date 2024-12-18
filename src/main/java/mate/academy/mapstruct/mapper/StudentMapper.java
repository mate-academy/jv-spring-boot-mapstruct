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

    @Mapping(source = "subjects", target = "subjectIds",
            qualifiedByName = "fromSubjectsToSubjectsIds")
    @Mapping(source = "group", target = "groupId",
            qualifiedByName = "fromGroupToGroupId")
    StudentDto toDto(Student student);

    @Mapping(source = "group", target = "groupId",
            qualifiedByName = "fromGroupToGroupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "subjectsIds", target = "subjects",
            qualifiedByName = "fromSubjectsIdsToSubjects")
    @Mapping(source = "groupId", target = "group",
            qualifiedByName = "fromIdGroupToGroup")
    Student toModel(CreateStudentRequestDto requestDto);
}
