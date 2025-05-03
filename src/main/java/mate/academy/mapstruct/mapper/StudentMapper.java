package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mappings({
        @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup"),
        @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "idListBySubject")
    })
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "idByGroup")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(target = "socialSecurityNumber", ignore = true),
            @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById"),
            @Mapping(target = "subjects", source = "subjects", qualifiedByName = "subjectListByIds")
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> subjectIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectIds);
    }

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects().stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjects);
    }
}
