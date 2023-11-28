package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = GroupMapper.class
)
public interface StudentMapper {
    @Mapping(target = "subjectIds", ignore = true)
    @Mapping(target = "groupId", source = "group.id")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto studentDto, Student student) {
        studentDto.setSubjectIds(student.getSubjects().stream()
                .map(Subject::getId)
                .toList());
    }

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        student.setSubjects(requestDto.subjects().stream()
                .map(Subject::new)
                .toList());
    }
}
