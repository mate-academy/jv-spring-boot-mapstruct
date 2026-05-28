package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "subjects", source = "subjects")
    Student toModel(CreateStudentRequestDto requestDto);

    default Long map(Group group) {
        return group == null ? null : group.getId();
    }

    default Long mapSubjectToId(Subject subject) {
        return subject.getId();
    }

    default Subject mapIdToSubject(Long id) {
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
