package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {GroupMapper.class, SubjectMapper.class}, componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "groupId", source = "group",
            qualifiedByName = "getGroupId")
    @Mapping(target = "subjectIds", source = "subjects",
                        qualifiedByName = "getSubjectsIds")
    StudentDto toDto(Student student);

    @Mapping(target = "groupId", source = "group.id")
    StudentWithoutSubjectsDto toEmployeeWithoutSubjectsDto(Student student);

    @Mapping(target = "group", source = "groupId",
            qualifiedByName = "groupById")
    @Mapping(target = "subjects", source = "subjects",
            qualifiedByName = "getSubjects")
    Student toModel(CreateStudentRequestDto requestDto);
}
