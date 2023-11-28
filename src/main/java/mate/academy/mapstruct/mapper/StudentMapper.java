package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface StudentMapper {
    StudentDto toDto(Student student);

    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    Student toModel(CreateStudentRequestDto requestDto);

    @AfterMapping
    default void setSubjectIds(@MappingTarget StudentDto dto, Student student) { 
        List<Long> subjectIds = new ArrayList<>();
        for (Subject subject : student.getSubjects()) {
            subjectIds.add(subject.getId());
        }
        dto.setSubjectIds(subjectIds);
        dto.setGroupId(student.getGroup().getId());
    }

    @AfterMapping
    default void setGroupId(@MappingTarget StudentWithoutSubjectsDto dto, Student student) { 
        dto.setGroupId(student.getGroup().getId());
    }

    @AfterMapping
    default void getGroupFromId(@MappingTarget Student student,
            CreateStudentRequestDto dto) {
        student.setGroup(new Group(dto.groupId()));
    }

    default List<Subject> getSubjectsFromIds(List<Long> ids) {
        return ids.stream()
                .map(id -> new Subject(id))
                .toList();
    } 
}
