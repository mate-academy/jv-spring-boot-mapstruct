package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.config.MapperConfig;
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

    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "mapSubjectsToIds")
    @Mapping(target = "groupId", source = "group.id")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId",source = "group", qualifiedByName = "groupIdFromStudent")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", source = "subjects",
            qualifiedByName = "mapSubjectsToListOfSubjects")
    @Mapping(target = "group.id", source = "groupId")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).toList();
    }

    @Named("groupIdFromStudent")
    default Long groupIdFromStudent(Group group) {
        return group.getId();
    }

    @Named("mapSubjectsToListOfSubjects")
    default List<Subject> mapSubjectsToListOfSubjects(List<Long> list) {
        if (list == null) {
            return new ArrayList<>();
        }

        return list.stream()
                .map(id -> {
                    Subject s = new Subject();
                    s.setId(id);
                    return s;
                })
                .collect(Collectors.toList());
    }
}
