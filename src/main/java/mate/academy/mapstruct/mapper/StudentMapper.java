package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        studentDto.setSubjectIds(student.getSubjects().stream()
                .map(Subject::getId)
                .toList());
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectById")
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(@MappingTarget Student student,
                                      CreateStudentRequestDto request
    ) {
        student.setSubjects(request.subjects().stream()
                .map(Subject::new)
                .toList());
    }
}
