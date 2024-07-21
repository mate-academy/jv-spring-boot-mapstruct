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
import org.mapstruct.Named;

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

    @Mapping(target = "subjects", qualifiedByName = "subjectByIds")
    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("idsBySubject")
    default List<Long> idsBySubject(List<Subject> subjectList) {
        return subjectList.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    @Named("subjectByIds")
    default List<Subject> subjectByIds(List<Long> idList) {
        return idList.stream()
                .map(Subject::new)
                .collect(Collectors.toList());
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return new Group(id);
    }
}
