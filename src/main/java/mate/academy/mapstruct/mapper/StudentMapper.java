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
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectIdsExtractor")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupFromIdExtractor")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectFromIdExtractor")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("groupFromIdExtractor")
    default Group mapGroups(Long groupId) {
        return new Group(groupId);
    }

    @Named("subjectFromIdExtractor")
    default List<Subject> mapSubjectsIds(List<Long> subjectIds) {
        return subjectIds.stream().map(Subject::new).toList();
    }

    @Named("subjectIdsExtractor")
    default List<Long> mapSubjects(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).toList();
    }
}
