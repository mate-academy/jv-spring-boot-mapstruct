package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group mapGroupIdToGroup(Long groupId) {
        return groupId != null ? new Group(groupId) : null;
    }

    default Subject mapSubjectIdToSubject(Long subjectId) {
        return subjectId != null ? new Subject(subjectId) : null;
    }

    @AfterMapping
    default void setSubjectIds(Student student, @MappingTarget StudentDto dto) {
        if (student.getSubjects() != null) {
            List<Long> ids = student.getSubjects().stream()
                    .map(Subject::getId)
                    .toList();
            dto.setSubjectIds(ids);
        }
    }
}
