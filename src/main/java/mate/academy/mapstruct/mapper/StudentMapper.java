package mate.academy.mapstruct.mapper;

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

@Mapper(config = MapperConfig.class, uses = GroupMapper.class)
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectsIds(
            @MappingTarget StudentDto studentDto,
            Student student
    ) {
        studentDto.setSubjectIds(student.getSubjects().stream()
                .map(Subject::getId)
                .toList());
    }

    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjects(
            @MappingTarget Student student,
            CreateStudentRequestDto requestDto
    ) {
        student.setSubjects(requestDto.subjectIds().stream()
                .map(Subject::new)
                .toList());
    }
}
