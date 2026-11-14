package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.StudentMapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = StudentMapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    StudentDto toDto(Student student);

    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "addSubjects")
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

}
