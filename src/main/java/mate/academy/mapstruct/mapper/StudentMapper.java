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
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjects")
    })
    StudentDto toDto(Student student);

    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "groupId", target = "group", qualifiedByName = "mapGroupId"),
            @Mapping(source = "subjects", target = "subjects"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "socialSecurityNumber", ignore = true)
    })
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Subject> map(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }

    @Named("mapSubjects")
    default List<Long> mapSubjects(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("mapGroupId")
    default Group mapGroupId(Long groupId) {
        if (groupId == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupId);
        return group;
    }
}
