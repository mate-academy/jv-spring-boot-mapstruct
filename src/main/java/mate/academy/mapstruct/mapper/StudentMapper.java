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

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectsToSubjectId")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(source = "groupId", target = "group", qualifiedByName = "idToGroup")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "idsToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("subjectsToSubjectId")
    default List<Long> subjectsToSubjects(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("idToGroup")
    default Group idToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return new Group(groupId);
    }

    @Named("idsToSubjects")
    default List<Subject> idsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }

}
