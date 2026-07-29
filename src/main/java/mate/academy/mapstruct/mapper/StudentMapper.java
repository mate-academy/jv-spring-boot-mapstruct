package mate.academy.mapstruct.mapper;

import java.util.Optional;
import mate.academy.mapstruct.config.GlobalMapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = {GroupMapper.class, SubjectMapper.class}
)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto requestDto, Student student) {
        Optional.ofNullable(student.getSubjects())
                .ifPresent(subjects -> requestDto.setSubjectIds(
                        subjects.stream()
                                .map(Subject::getId)
                                .toList()
                ));
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        Optional.ofNullable(requestDto.subjects())
                .ifPresent(ids -> student.setSubjects(
                        ids.stream()
                                .map(Subject::new)
                                .toList()
                ));
    }
}
