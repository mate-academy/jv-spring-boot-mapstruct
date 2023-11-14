package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group", qualifiedByName = "getIdFromGroup")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getIdsFromSubjects")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "getId")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsByIds")
    Student toModel(CreateStudentRequestDto requestDto);

    default Long getIdFromGroup(Group group) {
        return Optional.ofNullable(group.getId()).orElse(null);
    }

    default List<Long> getIdsFromSubjects(List<Subject> subjects) {
        return subjects.stream().map(s -> s.getId()).toList();
    }

    default Group getGroupById(Long id) {
        return Optional.
    }

    default List<Subject> getSubjectsByIds(List<Long> longList) {
        return longList.stream().map(Subject::new).toList();
    }
}
