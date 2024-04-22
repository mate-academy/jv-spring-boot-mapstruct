package mate.academy.mapstruct.mapper;

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

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectToId")
    })
    StudentDto toDto(Student student);

    @Mappings({
            @Mapping(source = "group.id", target = "groupId")
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById"),
            @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectById"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "socialSecurityNumber", ignore = true)
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("subjectToId")
    default Long subjectToId(Subject subject) {
        return subject.getId();
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

    @Named("subjectById")
    default Subject subjectById(Long id) {
        return Optional.ofNullable(id)
                .map(Subject::new)
                .orElse(null);
    }
}
