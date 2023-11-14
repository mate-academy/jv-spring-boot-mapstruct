package mate.academy.mapstruct.mapper;

import java.util.Optional;
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

    @Named("getGroupById")
    default Group getGroupById(Long id) {
        return new Group(Optional.ofNullable(id).orElse(null));
    }

    @Named("getIdFromGroup")
    default Long getIdFromGroup(Group group) {
        return Optional.ofNullable(group.getId()).orElse(null);
    }
}
