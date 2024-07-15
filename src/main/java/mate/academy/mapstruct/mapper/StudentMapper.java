package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds",
            expression = "java(subjectsToSubjectIds(student.getSubjects()))")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "subjects",
            expression = "java(subjectIdsToSubjects(requestDto.subjects()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Long> subjectsToSubjectIds(List<Subject> subjects) {
        return subjects != null ? subjects.stream().map(Subject::getId).toList() : null;
    }

    default List<Subject> subjectIdsToSubjects(List<Long> subjectIds) {
        return subjectIds != null ? subjectIds.stream().map(id -> {
            Subject subject = new Subject();
            subject.setId(id);
            return subject;
        }).toList() : null;
    }
}
