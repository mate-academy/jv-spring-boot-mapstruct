package mate.academy.mapstruct.mapper;

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group", expression = "java(mapToGroup(requestDto.groupId()))")
    @Mapping(target = "subjects", expression = "java(requestDto.subjects().stream()"
            + ".map(this::mapToSubject)"
            + ".toList())")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group mapToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupId);
        return group;
    }

    default Subject mapToSubject(Long subjectId) {
        if (subjectId == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(subjectId);
        return subject;
    }

    default Long mapToSubjectId(Subject subject) {
        if (subject == null) {
            return null;
        }
        return subject.getId();
    }
}
