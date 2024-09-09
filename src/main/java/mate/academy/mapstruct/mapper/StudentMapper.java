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
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, StudentMapper.class})
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "setSubjectIds")
    StudentDto toDto(Student student);

    @Named("setSubjectIds")
    default List<Long> setSubjectIds(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "setGroup")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "setSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("setGroup")
    default Group setGroup(Long id) {
        return new Group(id);
    }

    @Named("setSubjects")
    default List<Subject> setSubjects(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(Subject::new)
                .toList();
    }
}
