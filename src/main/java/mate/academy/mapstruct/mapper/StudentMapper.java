package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "toSubjectIds")
    StudentDto toDto(Student student);

    @Named("toSubjectIds")
    default List<Long> mapToSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "toSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("toSubjects")
    default List<Subject> mapToSubjectList(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
