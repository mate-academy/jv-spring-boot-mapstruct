package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
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
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects",
            qualifiedByName = "subjectToIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mappings({
            @Mapping(target = "group", source = "groupId",
                    qualifiedByName = "mapGroupIdToGroup"),
            @Mapping(target = "subjects", source = "subjects",
                    qualifiedByName = "mapSubjectIdsToSubjects")
    })
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapGroupIdToGroup")
    default Group mapGroupIdToGroup(Long id) {
        Group group = new Group();
        group.setId(id);
        return group;
    }

    @Named("mapSubjectIdsToSubjects")
    default List<Subject> mapSubjectIdsToSubjects(List<Long> ids) {
        if (ids == null) return new ArrayList<>();
        return ids.stream().map(id -> {
            Subject subject = new Subject();
            subject.setId(id);
            return subject;
        }).collect(Collectors.toList());
    }

    @Named("subjectToIds")
    default List<Long> subjectToIds(List<Subject> subjects) {
        if (subjects == null) return new ArrayList<>();
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }
}
