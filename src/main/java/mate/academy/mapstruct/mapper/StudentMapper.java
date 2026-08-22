package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(target = "subjectIds", ignore = true),
    })
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "socialSecurityNumber", ignore = true),
            @Mapping(target = "subjects", ignore = true),
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjectIds(@MappingTarget final StudentDto requestDto, final Student student) {
        final List<Long> subjects = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        requestDto.setSubjectIds(subjects);
    }

    @AfterMapping
    default void setSubjects(@MappingTarget final Student student,
                             final CreateStudentRequestDto requestDto) {
        final List<Subject> subjects = requestDto.subjects().stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjects);
    }
}
