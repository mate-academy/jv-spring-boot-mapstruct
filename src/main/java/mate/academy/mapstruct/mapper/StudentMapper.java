package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class, uses = {SubjectMapper.class, GroupMapper.class})
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group", qualifiedByName = "getIdFromGroup")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getIdsFromSubjects")
    StudentDto toDto(Student student);

    @Mapping(source = "group", target = "groupId", qualifiedByName = "getIdFromGroup")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId", qualifiedByName = "groupById")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "getSubjectsByIds")
    Student toModel(CreateStudentRequestDto requestDto);

    default void setSubject(@MappingTarget Student student, CreateStudentRequestDto requestDto) {
        List<Subject> subjects = requestDto.subjects().stream().map(Subject::new).toList();
        student.setSubjects(subjects);
    }
}
