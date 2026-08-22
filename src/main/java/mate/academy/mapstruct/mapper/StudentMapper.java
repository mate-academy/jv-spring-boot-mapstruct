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
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "subjects", target = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Long map(Subject subject) {
        return subject == null ? null : subject.getId();
    }

    default Long map(Group group) {
        return group == null ? null : group.getId();
    }

    default Subject mapSubject(Long id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }

    default Group mapGroup(Long id) {
        if (id == null) {
            return null;
        }
        Group group = new Group();
        group.setId(id);
        return group;
    }

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects == null
                ? null
                : subjects.stream().map(this::map).collect(Collectors.toList());
    }

    default List<Subject> mapIdsToSubjects(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream().map(this::mapSubject).collect(Collectors.toList());
    }
}

