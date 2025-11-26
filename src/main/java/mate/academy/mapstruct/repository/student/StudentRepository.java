package mate.academy.mapstruct.repository.student;

import java.util.List;
import java.util.Optional;
import mate.academy.mapstruct.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;

public interface StudentRepository {
    Student save(Student student);

    @EntityGraph(attributePaths = "subjects")
    Optional<Student> findById(Long id);

    List<Student> findAll();
}
