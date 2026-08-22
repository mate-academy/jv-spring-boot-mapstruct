package mate.academy.mapstruct.mapper;

import java.util.Collections;
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
    @Mapping(target = "groupId", source = "group", qualifiedByName = "mapGroupToGroupId")
    @Mapping(target = "subjectIds", source = "subjects",
            qualifiedByName = "mapSubjectsToSubjectIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "mapGroupToGroupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "mapGroupIdToGroup")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    List<Subject> map(List<Long> value);

    @Mapping(target = "id", source = "value")
    Subject map(Long value);

    @Named("mapGroupToGroupId")
    default Long mapGroupToGroupId(Group group) {
        return group != null ? group.getId() : null;
    }

    @Named("mapGroupIdToGroup")
    default Group mapGroupIdToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupId);
        return group;
    }

    @Named("mapSubjectsToSubjectIds")
    default List<Long> mapSubjectsToSubjectIds(List<Subject> subjects) {
        if (subjects == null) {
            return Collections.emptyList();
        }
        return subjects.stream()
            .map(Subject::getId)
            .toList();
    }
}
