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
import org.mapstruct.Mappings;

@Mapper(
        config = MapperConfig.class,
        uses = {
            GroupMapper.class
        }
)
public interface StudentMapper {
    @Mappings({
        @Mapping(target = "groupId", source = "group.id"),
        @Mapping(target = "subjectIds", ignore = true)
    })
    StudentDto toDto(Student student);

    @Mappings({
        @Mapping(target = "groupId", source = "group.id")
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
        @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById"),
        @Mapping(target = "subjects", ignore = true)
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjectsIds(@MappingTarget StudentDto studentDto, Student student) {
        studentDto.setSubjectIds(
                student.getSubjects()
                    .stream()
                    .map(Subject::getId)
                    .toList()
        );
    }

    @AfterMapping
    default void setSubjects(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        student.setSubjects(
                requestDto.subjects()
                        .stream()
                        .map(Subject::new)
                        .toList()
        );
    }
}
