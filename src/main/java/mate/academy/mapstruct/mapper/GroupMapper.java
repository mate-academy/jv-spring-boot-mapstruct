package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(config = MapperC)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    default Group getGroupById(Long id) {
        return new Group(Optional.ofNullable(id).orElse(null));
    }

    default Long getIdFromGroup(Group group) {
        return Optional.ofNullable(group.getId()).orElse(null);
    }
}
