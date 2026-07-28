package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.Optional;
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

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjects(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> subjectIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectIds);
    }

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "subjectsById")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

    @Named("subjectsById")
    default List<Subject> subjectsById(List<Long> ids) {
        return Optional.ofNullable(ids)
                .map(idList -> idList.stream()
                        .map(id -> {
                            Subject subject = new Subject();
                            subject.setId(id);
                            return subject;
                        })
                        .toList())
                .orElse(null);
    }

}
