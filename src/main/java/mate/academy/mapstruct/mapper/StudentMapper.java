package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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
    @Mapping(source = "group", target = "groupId", qualifiedByName = "idByGroup")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "idsBySubjects")
    StudentDto toDto(Student student);

    @Mapping(source = "group", target = "groupId", qualifiedByName = "idByGroup")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectsByIds")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("idByGroup")
    default Long idByGroup(Group group) {
        return group.getId();
    }

    @Named("idsBySubjects")
    default List<Long> idsBySubjects(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

    @Named("subjectsByIds")
    default List<Subject> subjectsByIds(List<Long> ids) {
        return ids.stream()
                .map(Subject::new)
                .toList();
    }
}
