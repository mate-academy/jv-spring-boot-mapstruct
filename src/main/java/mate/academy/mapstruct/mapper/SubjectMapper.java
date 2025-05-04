package mate.academy.mapstruct.mapper;

import java.util.ArrayList;
import java.util.List;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface SubjectMapper {

    SubjectDto toDto(Subject subject);

    Subject toModel(CreateSubjectRequestDto requestDto);

    @Named("subjectsByStudent")
    default List<Subject> subjectsByStudentId(Student student) {
        return student.getSubjects();
    }

    @Named("idsToSubjects")
    default List<Subject> idsToSubjects(List<Long> subjectIds) {
        List<Subject> subjects = new ArrayList<>();
        for (Long id : subjectIds) {
            subjects.add(new Subject(id));
        }
        return subjects;
    }

    @Named("subjectsToIds")
    default List<Long> subjectsToIds(List<Subject> subjects) {
        List<Long> subjectIds = new ArrayList<>();
        for (Subject s : subjects) {
            subjectIds.add(s.getId());
        }
        return subjectIds;
    }
}
