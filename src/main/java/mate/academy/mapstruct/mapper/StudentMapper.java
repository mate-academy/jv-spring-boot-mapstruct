package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", expression = "java(toSubjectIds(student.getSubjects()))")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId")
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Long> toSubjectIds(List<Subject> subjects) {
        return subjects == null ? List.of() :
                subjects.stream()
                        .map(Subject::getId)
                        .toList();
    }

    default Group map(Long groupId) {
        if (groupId == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupId);
        return group;
    }

    default List<Subject> map(List<Long> subjects) {
        if (subjects == null) {
            return null;
        }
        return subjects.stream()
                .map(s -> {
                    Subject subject = new Subject();
                    subject.setId(s);
                    return subject;
                })
                .toList();
    }
}
