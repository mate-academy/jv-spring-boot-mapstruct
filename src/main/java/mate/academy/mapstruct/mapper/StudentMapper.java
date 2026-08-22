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
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds",
            qualifiedByName = "mapSubjects")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group",
            qualifiedByName = "groupIdToGroup")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(source = "subjects", target = "subjects",
            qualifiedByName = "SubjectsIdToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjects")
    default List<Long> setSubjectId(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("groupIdToGroup")
    default Group groupIdToGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        return new Group(groupId);
    }

    @Named("SubjectsIdToSubjects")
    default List<Subject> subjectsIdToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
