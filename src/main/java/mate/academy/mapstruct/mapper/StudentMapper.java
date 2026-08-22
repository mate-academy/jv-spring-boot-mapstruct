package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectsToSubjectIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group")
    @Mapping(source = "subjects", target = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group toGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return new Group(groupId);
    }

    default List<Subject> toSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream().map(Subject::new).toList();
    }

    @Named("subjectsToSubjectIds")
    @IterableMapping(qualifiedByName = "subjectToId")
    default List<Long> subjectsToSubjectIds(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).toList();
    }

    @Named("subjectToId")
    default Long subjectToId(Subject subject) {
        return subject.getId();
    }
}
