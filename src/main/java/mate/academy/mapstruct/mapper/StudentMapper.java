package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "subjects",
                    target = "subjectIds",
                    qualifiedByName = "getIdsFromSubjects"),
            @Mapping(source = "group",
                    target = "groupId",
                    qualifiedByName = "getIdFromGroup")
    })
    StudentDto toDto(Student student);

    @Mapping(source = "group",
            target = "groupId",
            qualifiedByName = "getIdFromGroup")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "groupId",
                    target = "group",
                    qualifiedByName = "getGroupFromId"),
            @Mapping(source = "subjects",
                    target = "subjects",
                    qualifiedByName = "getSubjectsFromIds")
    })
    Student toModel(CreateStudentRequestDto requestDto);
}
