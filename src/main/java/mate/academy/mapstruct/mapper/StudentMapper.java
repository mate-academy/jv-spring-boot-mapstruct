package mate.academy.mapstruct.mapper;

import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        if (student.getSubjects() != null) {
            studentDto.setSubjectIds(
                    student.getSubjects().stream()
                            .map(Subject::getId)
                            .collect(Collectors.toList())
            );
        }
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setGroupAndSubjects(@MappingTarget Student student,
                                     CreateStudentRequestDto requestDto) {
        if (requestDto.groupId() != null) {
            student.setGroup(new Group(requestDto.groupId()));
        }
        if (requestDto.subjects() != null) {
            student.setSubjects(
                    requestDto.subjects().stream()
                            .map(Subject::new)
                            .collect(Collectors.toList())
            );
        }
    }
}
