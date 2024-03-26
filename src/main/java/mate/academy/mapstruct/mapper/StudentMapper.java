package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mappings({
            @Mapping(source = "group.id", target = "groupId"),
            @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "getSubjectIds")
    })
    StudentDto toDto(Student student);

    @Mappings({
            @Mapping(source = "group.id", target = "groupId")
    })
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsFromId")
    @Mapping(target = "group", source = "groupId", qualifiedByName = "getGroupById")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("getSubjectIds")
    default List<Long> getSubjectsIds(List<Subject> subjects) {
        return subjects.stream().map(Subject::getId).toList();
    }
}
