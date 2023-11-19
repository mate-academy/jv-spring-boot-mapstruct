package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class, uses = SubjectMapper.class)
public interface StudentMapper {

    StudentDto toDto(Student student);

    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "subjects", ignore = true)
    Student toModel(CreateStudentRequestDto requestDto);
}
