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

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    default Long map(Group group) {
        return group == null ? null : group.getId();
    }

    default Group map(Long groupId) {
        return groupId == null ? null : new Group(groupId);
    }

    default List<Subject> map(List<Long> subjectIds) {
        return subjectIds == null
                ? null
                : subjectIds.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects == null
                ? null
                : subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
