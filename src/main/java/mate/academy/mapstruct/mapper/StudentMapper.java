package mate.academy.mapstruct.mapper;

import java.util.List;
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

@Mapper(config = MapperConfig.class, uses = {GroupMapper.class, SubjectMapper.class})
public interface StudentMapper {
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "subjects", target = "subjectIds")
    StudentDto toDto(Student student);
    
    default Long subjectToId(Subject subject) {
        return subject.getId();
    }
    
    @Mapping(source = "group.id", target = "groupId")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);
    
    @Mapping(source = "subjects", target = "subjects", qualifiedByName = "idsToSubjects")
    @Mapping(source = "groupId", target = "group", qualifiedByName = "idToGroup")
    Student toModel(CreateStudentRequestDto requestDto);
    
    @Named("idsToSubjects")
    default List<Subject> idsToSubjects(List<Long> ids) {
        return ids == null
                ? null
                : ids.stream()
                .map(Subject::new)
                .toList();
    }
    
    @Named("idToGroup")
    default Group idToGroup(Long id) {
        return id == null ? null : new Group(id);
    }
}
