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

@Mapper(config = MapperConfig.class, uses = GroupMapper.class)
public interface StudentMapper {
    // Here we don't need to create anything like dummy Group object, so take group field,
    // id field from a that group field and save it to groupId target field of a Student object
    @Mapping(target = "subjectIds", ignore = true)
    @Mapping(target = "groupId", source = "group.id")
    StudentDto toDto(Student student);

    @AfterMapping
    default void initIdsFromSubjects(@MappingTarget StudentDto dto,
                                     Student student) {
        List<Long> subjectIds = student.getSubjects()
                .stream()
                .map(Subject::getId)
                .toList();
        dto.setSubjectIds(subjectIds);
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    // Searching for Named "groupFromId" in GroupMapper or other mappers that are used in here
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupFromId")
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void initSubjectsFromIds(@MappingTarget Student student,
                                     CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects()
                .stream()
                .map(Subject::new)
                .toList();
        student.setSubjects(subjects);
    }
}
