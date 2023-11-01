package mate.academy.mapstruct.repository.subject;

import java.util.List;
import mate.academy.mapstruct.model.Subject;

public interface SubjectRepository {
    Subject save(Subject subject);

    List<Subject> findAll();
}
