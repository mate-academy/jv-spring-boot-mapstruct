package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {

    @Mappings({
            @Mapping(source = "subjects", target = "subjectIds",
                    qualifiedByName = "subjectsToSubjectsIdsList"),
            @Mapping(source = "group", target = "groupId",
                    qualifiedByName = "groupToGroupId")
    })
    StudentDto toDto(Student student);

    @Mapping(source = "group", target = "groupId",
            qualifiedByName = "groupToGroupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "subjects", target = "subjects",
                    qualifiedByName = "subjectsIdsToSubjectsList"),
            @Mapping(source = "groupId", target = "group",
                    qualifiedByName = "idGroupToGroup")
    })
    Student toModel(CreateStudentRequestDto requestDto);
}
