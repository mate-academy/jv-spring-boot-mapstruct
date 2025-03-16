package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", uses = {
        GroupMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper {
    @Mapping(target = "subjectIds", ignore = true)
    @Mapping(source = "group.id", target = "groupId")
    StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", ignore = true)
    @Mapping(source = "groupId", target = "group", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(CreateStudentRequestDto requestDto, @MappingTarget Student student) {
        student.setSubjects(requestDto.subjects()
                .stream()
                .map(Subject::new)
                .toList());
    }

    @AfterMapping
    default void setSubjects(Student student, @MappingTarget StudentDto studentDto) {
        studentDto.setSubjectIds(
                student.getSubjects()
                        .stream()
                        .map(Subject::getId)
                        .toList());
    }
}
