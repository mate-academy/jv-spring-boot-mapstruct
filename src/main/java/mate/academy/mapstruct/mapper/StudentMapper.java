package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {GroupMapper.class, SubjectMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StudentMapper {

    // Student -> StudentDto (returns subjectIds list and groupId)
    @Mapping(target = "groupId",
            expression = "java(student.getGroup() != null ? student.getGroup().getId() : null)")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "toSubjectIds")
    StudentDto toDto(Student student);

    // Student -> StudentWithoutSubjectsDto (no subjectIds present; only groupId)
    @Mapping(target = "groupId",
            expression = "java(student.getGroup() != null ? student.getGroup().getId() : null)")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    /**
     * Map CreateStudentRequestDto -> Student
     * The request DTO has:
     *   - {@code Long groupId;}
     *   - {@code List<Long> subjects;}  // ids
     *
     * We create Group and Subject placeholders (only id set).
     */
    @Mapping(target = "group", source = "groupId", qualifiedByName = "mapGroupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "mapSubjectsByIds")
    Student toModel(CreateStudentRequestDto dto);

    // ---- helpers ----

    @Named("toSubjectIds")
    default List<Long> toSubjectIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("mapGroupById")
    default Group mapGroupById(Long id) {
        if (id == null) {
            return null;
        }
        Group group = new Group();
        group.setId(id);
        return group;
    }

    @Named("mapSubjectsByIds")
    default List<Subject> mapSubjectsByIds(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    Subject s = new Subject();
                    s.setId(id);
                    return s;
                })
                .collect(Collectors.toList());
    }
}
