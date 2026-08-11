package mate.academy.mapstruct.mapper;

import java.util.Collections;
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
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds")
    public abstract StudentDto toDto(Student student);

    @Mapping(source = "group.id", target = "groupId")
    public abstract StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(source = "groupId", target = "group", qualifiedByName = "toGroup")
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "toSubject")
    public abstract Student toModel(CreateStudentRequestDto requestDto);

    @Named("toSubject")
    public List<Subject> toSubject(List<Long> subjectIds) {
        return subjectIds != null ? subjectRepository
                .findAllById(subjectIds) : Collections.emptyList();
    }

    @Named("toGroup")
    public Group toGroup(Long groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }

    public Long toId(Subject subject) {
        return subject.getId();
    }
}
