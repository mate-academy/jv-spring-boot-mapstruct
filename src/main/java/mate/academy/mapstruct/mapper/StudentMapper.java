package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "groupId", source = "group.id"),
            @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getSubjectIds")
    })
    StudentDto toDto(Student student);

    @Mappings({
            @Mapping(target = "groupId", source = "group.id")
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById"),
            @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectsByIds"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "socialSecurityNumber", ignore = true)
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("getSubjectIds")
    default List<Long> getSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("groupById")
    default Group groupById(Long groupId) {
        return Optional.ofNullable(groupId)
                .map(Group::new)
                .orElse(null);
    }

    @Named("subjectsByIds")
    default List<Subject> subjectsByIds(List<Long> subjectsIds) {
        return subjectsIds.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }
}
