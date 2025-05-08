package mate.academy.mapstruct.repository.group;

import java.util.List;
import java.util.Optional;
import mate.academy.mapstruct.model.Group;

public interface GroupRepository {
    Group save(Group group);

    Optional<Group> findById(Long id);

    List<Group> findAll();
}
