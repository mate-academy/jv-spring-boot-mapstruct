package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import mate.academy.mapstruct.repository.group.GroupRepository;
import mate.academy.mapstruct.repository.subject.SubjectRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group", qualifiedByName = "mapGroupToId")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "mapSubjectsToIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group", qualifiedByName = "mapGroupToId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);

    default void setGroupAndSubjects(Student student, CreateStudentRequestDto requestDto,
                                     GroupRepository groupRepository,
                                     SubjectRepository subjectRepository) {
        if (requestDto.groupId() != null) {
            student.setGroup(groupRepository.findById(requestDto.groupId()).orElse(null));
        }
        if (requestDto.subjects() != null && !requestDto.subjects().isEmpty()) {
            student.setSubjects(subjectRepository.findAllById(requestDto.subjects()));
        }
    }

    @Named("mapGroupToId")
    default Long mapGroupToId(Group group) {
        return group != null ? group.getId() : null;
    }

    @Named("mapSubjectsToIds")
    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects != null ? subjects.stream()
                .map(Subject::getId)
                .toList() : null;
    }
}
