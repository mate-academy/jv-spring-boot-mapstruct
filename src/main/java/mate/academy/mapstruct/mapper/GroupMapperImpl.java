package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;

public class GroupMapperImpl implements GroupMapper {
    @Override
    public GroupDto toDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        return dto;
    }

    @Override
    public Group toModel(CreateGroupRequestDto requestDto) {
        Group group = new Group();
        group.setName(requestDto.name());
        return group;
    }
}
