package mate.academy.mapstruct.repository.subject;

import mate.academy.mapstruct.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
