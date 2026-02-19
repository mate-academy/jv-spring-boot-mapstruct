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
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    protected SubjectRepository subjectRepository;

    @Autowired
    protected GroupRepository groupRepository;

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects")
    public abstract StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    public abstract StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    public abstract Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    protected void setRelations(
            @MappingTarget Student student,
            CreateStudentRequestDto requestDto
    ) {

        student.setSubjects(
                subjectRepository.findAllById(requestDto.subjects())
        );

        if (requestDto.groupId() != null) {
            Group group = groupRepository.findById(requestDto.groupId()).orElseThrow();
            student.setGroup(group);
        }
    }

    protected List<Long> mapSubjectsToIds(List<Subject> subjects) {
        return subjects == null
                ? null
                : subjects.stream()
                .map(Subject::getId)
                .toList();
    }
}
