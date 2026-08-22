package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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

@Mapper(config = MapperConfig.class,
        uses = {GroupMapper.class, SubjectMapper.class},
        componentModel = "spring")
public interface StudentMapper {
    @Mappings({
            @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup"),
            @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "subjectById")
    })
    StudentDto toDto(Student student);

    @Mappings({
            @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup")
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById"),
            @Mapping(target = "subjects", source = "subjects", qualifiedByName = "idBySubject")
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("groupById")
    default Group groupById(Long groupId) {
        return Optional.ofNullable(groupId)
                .map(Group::new)
                .orElse(null);
    }

    @Named("idByGroup")
    default Long idByGroup(Group group) {
        return Optional.ofNullable(group)
                .map(groupId -> group.getId())
                .orElse(null);
    }

    @Named("idBySubject")
    default List<Subject> idBySubject(List<Long> ids) {
        return Optional.ofNullable(ids)
                .map(list -> list.stream()
                        .map(Subject::new)
                        .toList())
                .orElse(null);
    }

    @Named("subjectById")
    default List<Long> subjectById(List<Subject> subjects) {
        return Optional.ofNullable(subjects)
                .map(list -> list.stream()
                        .map(Subject::getId)
                        .toList())
                .orElse(null);
    }
}
