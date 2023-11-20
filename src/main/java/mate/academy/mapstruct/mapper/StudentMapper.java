package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GenericMapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    StudentDto toDto(Student student);

    default List<Long> mapSubjects(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId)
                .toList();
    }

    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group.id", source = "requestDto.groupId")
    @Mapping(target = "subjects", source = "requestDto.subjectIds")
    Student toModel(CreateStudentRequestDto requestDto);

    default List<Subject> mapLongs(List<Long> longs) {
        return longs.stream()
                .map(Subject::new)
                .toList();
    }
}
