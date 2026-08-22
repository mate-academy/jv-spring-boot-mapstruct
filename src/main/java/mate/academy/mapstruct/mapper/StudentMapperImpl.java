package mate.academy.mapstruct.mapper;

import java.util.List;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.model.Student;
import mate.academy.mapstruct.model.Subject;

//@Component
public class StudentMapperImpl implements StudentMapper {
    private final GroupMapper groupMapper;

    public StudentMapperImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setGroupId(student.getGroup().getId());
        setSubjectsToDto(student.getSubjects(), studentDto);
        return studentDto;
    }

    @Override
    public StudentWithoutSubjectsDto toStudentWithoutSubjectsDto(Student student) {
        StudentWithoutSubjectsDto studentNoSubgDto =
                new StudentWithoutSubjectsDto();
        studentNoSubgDto.setId(student.getId());
        studentNoSubgDto.setName(student.getName());
        studentNoSubgDto.setEmail(student.getEmail());
        studentNoSubgDto.setGroupId(student.getGroup().getId());
        return studentNoSubgDto;
    }

    @Override
    public Student toModel(CreateStudentRequestDto requestDto) {
        Student student = new Student();
        student.setName(requestDto.name());
        student.setEmail(requestDto.email());
        student.setGroup(groupMapper.getById(requestDto.groupId()));
        setSubjects(student, requestDto.subjects());
        return student;
    }

    // method setSubjectsToDto using loop
    /*
    private void setSubjectsToDto(List<Subject> listOfSubjects,
                                  StudentDto studentDto) {
        List<Long> subjectsList = new ArrayList<>();
        for (Subject subject : listOfSubjects) {
           Long subjId = subject.getId();
           subjectsList.add(subjId);
        }
        studentDto.setSubjectIds(subjectsList);
    }

*/

    // method setSubjects using stream
    private void setSubjects(Student student, List<Long> subjectIds) {
        List<Subject> subjects = subjectIds
                .stream()
                .map(id -> new Subject(id))
                .toList();
        student.setSubjects(subjects);
    }

    // method setSubjectsToDto using stream();
    private void setSubjectsToDto(List<Subject> subjectList,
                                  StudentDto studentDto) {
        List<Long> subjIdList = subjectList
                .stream()
                .map(subject -> subject.getId())
                .toList();
        studentDto.setSubjectIds(subjIdList);
    }

    // method setSubjects using loop
    /*
    private void setSubjects (Student student,
                              List<Long> subjectIds) {
        List<Subject> subjects = new ArrayList<>();
        for (Long id : subjectIds) {
            Subject subject = new Subject(id);
            subjects.add(subject);
        }
        student.setSubjects(subjects);
    }
*/

    /*
        private void setSubjects (Student student,
                              List<Long> subjectIds) {
        List<Subject> subjects = new ArrayList<>();
        for (Long id : subjectIds) {
            Subject subject = new Subject(id);
            subjects.add(subject);
        }
        student.setSubjects(subjects);
    }
     */
}
