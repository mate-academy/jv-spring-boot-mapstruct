package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "subjectIds",
            source = "subjects",
            qualifiedByName = "mapSubjectsToLongs")
    @Mapping(target = "groupId",
            source = "group.id")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId",
            source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects",
            source = "requestDto.subjects",
            qualifiedByName = "mapLongsToSubjects")
    @Mapping(target = "group",
            source = "groupId",
            qualifiedByName = "longToGroup")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapLongsToSubjects")
    default List<Subject> map(List<Long> subjectIds) {
        return subjectIds.stream()
            .map(Subject::new)
            .collect(Collectors.toList());
    }

    @Named("mapSubjectsToLongs")
    default List<Long> mapSubjectsToLongs(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).collect(Collectors.toList());
    }

    @Named("longToGroup")
    default Group longToGroup(Long groupId) {
        return new Group(groupId);
    }
}
