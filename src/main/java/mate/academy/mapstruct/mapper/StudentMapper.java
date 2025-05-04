package mate.academy.mapstruct.mapper;

import java.util.List;
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
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {

    @Mapping(target = "subjectIds", ignore = true)
    @Mapping(target = "groupId", source = "group.id")
    StudentDto toDto(Student student);

    @AfterMapping
    default void setSubjectsIds(Student student, @MappingTarget StudentDto studentDto) {
        List<Long> subjectsIds = student.getSubjects()
                .stream()
                .map(Subject::getId)
                .toList();
        studentDto.setSubjectIds(subjectsIds);
    }

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsByIds")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("getSubjectsByIds")
    default List<Subject> getSubjectsByIds(List<Long> subjectsIds) {
        return subjectsIds.stream().map(id -> {
            Subject subject = new Subject();
            subject.setId(id);
            return subject;
        }).toList();
    }
}
