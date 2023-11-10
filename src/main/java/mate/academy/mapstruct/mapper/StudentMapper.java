package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects",
            qualifiedByName = "mapSubjectsToSubjectsIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "socialSecurityNumber", ignore = true)
    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "mapSubjectIdsToSubjects")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("mapSubjectsToSubjectsIds")
    default List<Long> mapSubjectsToSubjectsIds(List<Subject> subjects) {
        if (subjects == null) {
            return Collections.emptyList();
        }
        List<Long> longList = new ArrayList<>();
        for (Subject subject : subjects) {
            longList.add(subject.getId());
        }
        return longList;
    }

    @Named("mapSubjectIdsToSubjects")
    default List<Subject> mapSubjectIdsToSubjects(List<Long> subjectIds) {
        if (subjectIds == null) {
            return Collections.emptyList();
        }
        return subjectIds.stream()
                .map(subjectId -> {
                    Subject subject = new Subject();
                    subject.setId(subjectId);
                    return subject;
                })
                .collect(Collectors.toList());
    }
}
