package mate.academy.mapstruct.repository.group;

import java.util.List;
import mate.academy.mapstruct.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group save(Group group);

    List<Group> findAll();
}
