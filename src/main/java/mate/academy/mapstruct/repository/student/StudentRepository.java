package mate.academy.mapstruct.repository.student;

import java.util.List;
import java.util.Optional;
import mate.academy.mapstruct.dto.model.Student;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(Long id);

    List<Student> findAll();
}
