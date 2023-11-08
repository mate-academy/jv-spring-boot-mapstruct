package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfiguration;
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

@Mapper(config = MapperConfiguration.class)
public interface StudentMapper {
    StudentDto toDto(Student student);

    @AfterMapping
    default void init(@MappingTarget StudentDto dto, Student student) {
        dto.setGroupId(student.getGroup().getId());
        dto.setSubjectIds(student.getSubjects().stream()
                .map(Subject::getId)
                .toList());
    }

    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @AfterMapping
    default void initGroupId(@MappingTarget StudentWithoutSubjectsDto dto, Student student) {
        dto.setGroupId(student.getGroup().getId());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void initGroup(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        Group group = new Group();
        group.setId(requestDto.groupId());
        student.setGroup(group);
    }

    default List<Subject> map(List<Long> value) {
        return value.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .toList();
    }
}
