package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class,SubjectMapper.class})
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "subjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "idsToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

}
