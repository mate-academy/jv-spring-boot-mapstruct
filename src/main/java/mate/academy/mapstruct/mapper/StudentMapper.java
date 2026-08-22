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

@Mapper(componentModel = "spring", uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "mapGroupIdToGroup")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "mapSubjectIdsToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapGroupIdToGroup")
    default Group mapGroupIdToGroup(Long groupId) {
        return groupId != null ? new Group(groupId) : null;
    }

    @Named("mapSubjectIdsToSubjects")
    default List<Subject> mapSubjectIdsToSubjects(List<Long> subjectsIds) {
        return subjectsIds != null ? subjectsIds.stream()
                .map(Subject::new)
                .toList() : List.of();
    }

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects != null ? subjects.stream().map(Subject::getId).toList() : List.of();
    }
}
