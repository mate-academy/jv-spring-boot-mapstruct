package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", expression = "java(mapSubjects(student.getSubjects()))")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", expression = "java(new Group(requestDto.groupId()))")
    @Mapping(target = "subjects", expression = "java(mapSubjectIds(requestDto.subjects()))")
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Long> mapSubjects(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).collect(Collectors.toList());
    }

    default List<Subject> mapSubjectIds(List<Long> subjectIds) {
        return subjectIds.stream().map(Subject::new).collect(Collectors.toList());
    }
}
