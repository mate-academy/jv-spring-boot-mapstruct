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
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "mapSubjectIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "mapGroup")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "mapSubjects")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapGroup")
    default Group mapGroup(Long id) {
        return id == null ? null : new Group(id);
    }

    @Named("mapSubjects")
    default List<Subject> mapSubjects(List<Long> ids) {
        return ids == null ? null : ids.stream()
                .map(Subject::new)
                .toList();
    }

    @Named("mapSubjectIds")
    default List<Long> mapSubjectIds(List<Subject> subjects) {
        return subjects == null ? null : subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
