package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "mapSubjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "mapIdsToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return List.of();
        }
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Named("mapIdsToSubjects")
    default List<Subject> mapIdsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return List.of();
        }
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
