package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;


import mate.academy.mapstruct.model.Subject;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(target = "subjectIds", ignore = true)
    StudentDto toDto(Student student);

    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group.id", qualifiedByName = "groupById")
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void subjectById(CreateStudentRequestDto createStudentRequestDto,
                             @MappingTarget Student student) {
        List<Subject> subjectList = createStudentRequestDto.subjects().stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjectList);
    }

    @AfterMapping
    default void idsBySubjects(@MappingTarget StudentDto studentDto, Student student) {
        List<Long> listIds = student.getSubjects().stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(listIds);
    }

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }
}
