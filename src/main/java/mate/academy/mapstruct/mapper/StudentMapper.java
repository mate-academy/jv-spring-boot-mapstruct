package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group idToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }

        Group group = new Group();
        group.setId(groupId);
        return group;
    }

    default Subject idToSubject(Long subjectId) {
        if (subjectId == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setId(subjectId);
        return subject;
    }

    default List<Subject> idsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }

        return subjectIds.stream().map(this::idToSubject).toList();
    }

    default Long groupToId(Group group) {
        return group == null ? null : group.getId();
    }

    default List<Long> subjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }

        return subjects.stream().map(Subject::getId).toList();
    }
}
