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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId")
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Long> toIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    default List<Subject> toSubjects(List<Long> subjects) {
        return subjects.stream()
                .map(Subject::new)
                .toList();
    }

    default Group toGroup(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

}
