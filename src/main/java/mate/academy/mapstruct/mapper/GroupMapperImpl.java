package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;

public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupDto toDto(Group group) {
        if (group == null) {
            return null;
        }
        GroupDto groupDto = new GroupDto();
        groupDto.setName(groupDto.getName());
        groupDto.setId(groupDto.getId());

        return groupDto;
    }

    @Override
    public Group toModel(CreateGroupRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Group group = new Group();
        group.setName(requestDto.name());
        return group;
    }
}
