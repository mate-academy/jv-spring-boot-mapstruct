package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "mapIdsToSubjects")
    @Mapping(source = "groupId", target = "group", qualifiedByName = "mapIdToGroup")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapIdsToSubjects")
    default List<Subject> mapIdsToSubjects(List<Long> subjectIds) {
        return subjectIds.stream().map(Subject::new).toList();
    }

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjectIds) {
        return subjectIds.stream().map(Subject::getId).toList();
    }

    @Named("mapIdToGroup")
    default Group mapIdToGroup(Long id) {
        return new Group(id);
    }
}
