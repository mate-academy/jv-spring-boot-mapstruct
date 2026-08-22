package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.model.Group;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperHelper {
    public Group mapGroup(Long id) {
        Group group = new Group();
        group.setId(id);
        return group;
    }
}
