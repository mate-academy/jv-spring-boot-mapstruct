package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupFromId")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectsFromIds")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("groupFromId")
    default Group groupFromId(Long id) {
        return id == null ? null : new Group(id);
    }

    @Named("subjectsFromIds")
    default List<Subject> subjectsFromIds(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("subjectsToIds")
    default List<Long> subjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
