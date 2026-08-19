package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class, uses = SubjectMapper.class)
public interface StudentMapper {
    @Mappings({
            @Mapping(target = "groupId", source = "group.id"),
            @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "setSubjectIds")
    })
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(target = "group.id", source = "groupId"),
            @Mapping(target = "subjects", source = "subjects", qualifiedByName = "setSubjectsById")
    })
    Student toModel(CreateStudentRequestDto requestDto);
}
