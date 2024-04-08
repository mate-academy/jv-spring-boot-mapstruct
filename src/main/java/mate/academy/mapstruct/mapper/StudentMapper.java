package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = mate.academy.mapstruct.config.MapperConfig.class, uses = GroupMapper.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> subjectsIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectsIds);
    }

    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects().stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjects);
    }
}
