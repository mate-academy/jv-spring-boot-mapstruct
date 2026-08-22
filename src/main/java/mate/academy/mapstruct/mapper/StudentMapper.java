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
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "toIdSubject")
    })
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "groupId", target = "group.id"),
            @Mapping(source = "subjects", target = "subjects", qualifiedByName = "byIdSubject")
    })
    Student toModel(CreateStudentRequestDto requestDto);
}
