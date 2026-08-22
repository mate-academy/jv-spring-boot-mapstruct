package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
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
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setGroupAndSubjects(@MappingTarget Student student,
                                     CreateStudentRequestDto requestDto) {
        if (requestDto.groupId() != null) {
            Group group = new Group();
            group.setId(requestDto.groupId());
            student.setGroup(group);
        }

        if (requestDto.subjects() != null) {
            List<Subject> subjects = requestDto.subjects().stream()
                    .map(id -> {
                        Subject subject = new Subject();
                        subject.setId(id);
                        return subject;
                    })
                    .collect(Collectors.toList());
            student.setSubjects(subjects);
        }
    }

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        if (student.getSubjects() != null) {
            studentDto.setSubjectIds(student.getSubjects().stream()
                    .map(Subject::getId)
                    .collect(Collectors.toList()));
        }
    }
}
