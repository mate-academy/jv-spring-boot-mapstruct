package mate.academy.mapstruct.mapper;

import java.util.Collections;
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
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = GroupMapper.class)
public interface StudentMapper {
    @Mapping(source = "group", target = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @Mapping(source = "group", target = "groupId", qualifiedByName = "groupById")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubject(CreateStudentRequestDto requestDto, @MappingTarget Student student) {
        if (requestDto.subjects() != null) {
            List<Subject> subjects = requestDto.subjects().stream()
                    .map(Subject::new)
                    .toList();
            student.setSubjects(subjects);
        } else {
            student.setSubjects(Collections.emptyList());
        }

    }

    @AfterMapping
    default void setSubjectId(@MappingTarget StudentDto studentDto, Student student) {
        if (student.getSubjects() != null) {
            List<Long> subjectsId = student.getSubjects().stream()
                    .map(Subject::getId)
                    .toList();
            studentDto.setSubjectIds(subjectsId);
        } else {
            studentDto.setSubjectIds(Collections.emptyList());
        }
    }

    @Named("groupById")
    default Long mapGroupToGroupId(Group group) {
        return group != null ? group.getId() : null;
    }
}
