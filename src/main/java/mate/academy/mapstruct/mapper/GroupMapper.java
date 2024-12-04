package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    @Named("setGroupId")
    default Long setGroupId(Group group) {
        return group.getId();
    }

    @Named("setGroup")
    default Group setGroup(Long groupId) {
        return new Group(groupId);
    }
}
