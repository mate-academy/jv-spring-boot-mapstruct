package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapperHelper {
    public Subject map(Long id) {
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
