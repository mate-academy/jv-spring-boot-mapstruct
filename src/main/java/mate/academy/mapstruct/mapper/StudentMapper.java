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
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group mapGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return new Group(groupId);
    }

    default List<Subject> mapSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }

    default Long mapSubjectToId(Subject subject) {
        return subject.getId();
    }
}
