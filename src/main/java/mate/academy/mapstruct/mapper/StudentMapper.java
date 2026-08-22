package mate.academy.mapstruct.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, StudentMapper.class})
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "subjectIds", source = "subjects", qualifiedByName = "getSubjectsId")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "subjects", source = "subjects", qualifiedByName = "createSubjectsId")
    Student toModel(CreateStudentRequestDto requestDto);

    @Named("getSubjectsId")
    default List<Long> getSubjectsId(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getId) // Assuming Subject has a getId() method
                .collect(Collectors.toList());
    }

    @Named("createSubjectsId")
    default List<Subject> createSubjectsId(List<Long> subjectIds) {
        return subjectIds.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id); // Create a new Subject with just the ID
                    return subject;
                })
                .collect(Collectors.toList());
    }
}
