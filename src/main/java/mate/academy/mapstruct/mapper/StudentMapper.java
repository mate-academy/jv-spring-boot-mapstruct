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
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "subjectIdsBySubjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectsByIds")
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @Named(value = "groupById")
    default Group setGroup(Long groupId) {
        return new Group(groupId);
    }

    @Named(value = "subjectsByIds")
    default List<Subject> setSubjects(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }

    @Named("subjectIdsBySubjects")
    default List<Long> setSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
