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
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(source = "groupId", target = "group", qualifiedByName = "mapToGroup")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "mapToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapToSubjects")
    default List<Subject> mapIdsToSubjects(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .collect(Collectors.toList());
    }

    @Named("mapToGroup")
    default Group mapToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupId);
        return group;
    }

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
