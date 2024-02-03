package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    @Mapping(target = "id", ignore = true)
    Group toModel(CreateGroupRequestDto requestDto);

    @Named("getGroupId")
    default Long getGroupId(Group group) {
        return group.getId();
    }

    @Named("getGroupById")
    default Group getGroupById(Long id) {
        return new Group(id);
    }
}