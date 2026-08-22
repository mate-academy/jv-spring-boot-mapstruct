package mate.academy.mapstruct.mapper;

import java.util.List;
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

    @Mapping(source = "groupId", target = "group")
    @Mapping(source = "subjects", target = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Long subjectToId(Subject subject) {
        return subject == null ? null : subject.getId();
    }

    default List<Long> subjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream().map(Subject::getId).toList();
    }

    default Group groupFromId(Long groupId) {
        return groupId == null ? null : new Group(groupId);
    }

    default Subject subjectFromId(Long subjectId) {
        return subjectId == null ? null : new Subject(subjectId);
    }
}
