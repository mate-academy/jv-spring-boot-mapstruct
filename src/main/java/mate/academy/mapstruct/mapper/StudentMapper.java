package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
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

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "mapGroupFromId")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "mapSubjectsFromIds")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjectsToIds")
    static List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects == null ? null : subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("mapSubjectsFromIds")
    static List<Subject> mapSubjectsFromIds(List<Long> subjectIds) {
        return subjectIds == null ? null : subjectIds.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("mapGroupFromId")
    static Group mapGroupFromId(Long id) {
        return id == null ? null : new Group(id);
    }
}
