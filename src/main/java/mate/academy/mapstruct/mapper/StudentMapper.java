package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(source = "subjects", target = "subjectIds")
    })
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group.id ")
    Student toModel(CreateStudentRequestDto requestDto);

    default Subject mapIdToSubject(Long id) {
        if (id == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setId(id);

        return subject;
    }

    default Long mapSubjectIdToId(Subject subject) {
        if (subject == null) {
            return null;
        }
        return subject.getId();
    }
}
