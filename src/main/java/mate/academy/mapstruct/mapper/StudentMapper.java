package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> subjectsIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectsIds);
    }

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", ignore = true)
    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects().stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjects);
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map((Group::new))
                .orElse(null);

    }
}
