package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto toDto(Group group);

    @Mapping(target = "id", ignore = true)
    Group toModel(CreateGroupRequestDto requestDto);

    default Long map(Group group) {
        return group != null ? group.getId() : null;
    }

    default Group map(Long groupId) {
        return groupId != null ? new Group(groupId) : null;
    }
}
