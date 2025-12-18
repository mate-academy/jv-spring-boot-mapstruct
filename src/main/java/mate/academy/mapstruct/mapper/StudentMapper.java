package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Long mapGroupToId(Group group) {
        if (group != null) {
            return group.getId();
        }
        return null;
    }

    default Group mapIdToGroup(Long groupId) {
       if (groupId != null) {
           return new Group(groupId);
       }
       return null;
    }

    default List<Subject> mapIdToSubjects(List<Long> subjectIds) {
        if (subjectIds != null) {
            return subjectIds.stream()
                    .map(Subject::new)
                    .toList();
        }
        return null;
    }

    default List<Long> mapSubjectsToGroups(List<Subject> subjects) {
        if (subjects != null) {
            return subjects.stream()
                    .map(Subject::getId)
                    .toList();
        }
        return null;
    }
}
