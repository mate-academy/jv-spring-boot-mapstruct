package mate.academy.mapstruct.service.group;

import java.util.List;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;

public interface GroupService {
    GroupDto save(CreateGroupRequestDto requestDto);

    List<GroupDto> findAll();
}
