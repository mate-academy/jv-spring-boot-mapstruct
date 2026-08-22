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
        @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup"),
        @Mapping(target = "subjectIds",
            source = "subjects",
            qualifiedByName = "idListBySubject")
    })
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
        @Mapping(target = "socialSecurityNumber", ignore = true),
        @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById"),
        @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectListByIds")

    })
    Student toModel(CreateStudentRequestDto requestDto);
}
