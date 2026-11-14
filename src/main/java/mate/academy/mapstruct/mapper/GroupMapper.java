package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.GroupMapperConfig;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;

@Mapper(config = GroupMapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);
}
