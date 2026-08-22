package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {SubjectMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group")
    Student toModel(CreateStudentRequestDto requestDto);

    default Group groupIdToGroup(Long id) {
        Group group = new Group();
        group.setId(id);
        return group;
    }

    default Subject idToSubject(Long id) {
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }

    default Long subjectToId(Subject subject) {
        return subject.getId();
    }
}
