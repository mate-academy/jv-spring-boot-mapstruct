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
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> subjectIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectIds);
    }

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void createGroup(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        student.setGroup(new Group(requestDto.groupId()));
    }

    @AfterMapping
    default void createSubjects(@MappingTarget Student student,
                                CreateStudentRequestDto requestDto) {
        student.setSubjects(map(requestDto.subjects()));
    }

    // Single-item mapping
    default Subject map(Long id) {
        return new Subject(id);
    }

    // List mapping
    default List<Subject> map(List<Long> ids) {
        return ids.stream()
                .map(this::map) // Reuse single-item mapping
                .toList();
    }
}

