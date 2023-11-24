package mate.academy.mapstruct.repository.group;

import java.util.List;
import mate.academy.mapstruct.dto.model.Group;

public interface GroupRepository {
    Group save(Group group);

    List<Group> findAll();
}
