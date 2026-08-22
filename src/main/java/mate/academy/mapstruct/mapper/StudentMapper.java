package mate.academy.mapstruct.mapper;

import java.util.List;
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

    @AfterMapping
    default void setSubjectIds(Student student, @MappingTarget StudentDto studentDto) {
        List<Long> ids = student.getSubjects()
                .stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(ids);
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @AfterMapping
    default void setGroupId(Student student,
                            @MappingTarget StudentWithoutSubjectsDto studentWithoutSubjectsDto) {
        studentWithoutSubjectsDto.setGroupId(student.getGroup().getId());
    }

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setGroupById(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        Long groupId = requestDto.groupId();
        student.setGroup(new Group(groupId));
    }

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects().stream().map(Subject::new).toList();
        student.setSubjects(subjects);
    }
}
