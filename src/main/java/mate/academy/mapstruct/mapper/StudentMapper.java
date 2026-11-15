package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", expression = "java(mapSubjectsToIds(student.getSubjects()))")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", expression = "java(new Group(studentDto.getGroupId()))")
    @Mapping(target = "subjects", expression = "java(mapIdsToSubjects(studentDto.getSubjectIds()))")
    Student toModel(StudentDto studentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", expression = "java(new Group(requestDto.groupId()))")
    @Mapping(target = "subjects", expression = "java(mapIdsToSubjects(requestDto.subjects()))")
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    default List<Subject> mapIdsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return null;
        }
        return subjectIds.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .collect(Collectors.toList());
    }
}
