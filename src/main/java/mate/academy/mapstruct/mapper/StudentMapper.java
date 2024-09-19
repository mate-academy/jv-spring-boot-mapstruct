package mate.academy.mapstruct.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjectsToIds")
    @Mapping(target = "id", source = "id")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "id", source = "id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "subjects", ignore = true) // Assuming subjects will be set separately
    @Mapping(target = "id", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return Collections.emptyList();
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
