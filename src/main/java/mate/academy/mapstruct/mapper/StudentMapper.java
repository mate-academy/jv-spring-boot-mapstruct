package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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
    @Mapping(target = "groupId", source = "group", qualifiedByName = "groupIdByEntity")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "subjectIdsByEntity")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "groupIdByEntity")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectsById")
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("groupIdByEntity")
    default Long groupIdByEntity(Group group) {
        return group.getId();
    }

    @Named("subjectIdsByEntity")
    default List<Long> subjectIdsByEntity(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("subjectsById")
    default List<Subject> subjectsById(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id).map(Group::new).orElse(null);
    }
}
