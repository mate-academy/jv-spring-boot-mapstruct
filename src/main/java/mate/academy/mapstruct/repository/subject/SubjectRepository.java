package mate.academy.mapstruct.repository.subject;

import java.util.List;
import java.util.Optional;
import mate.academy.mapstruct.model.Subject;

public interface SubjectRepository {
    Optional<Subject> findById(Long id);

    Subject save(Subject subject);

    List<Subject> saveAll(List<Subject> subjects);

    List<Subject> findAll();
}
