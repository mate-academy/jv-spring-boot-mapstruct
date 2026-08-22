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

    @Named("idGroupToGroup")
    default Group idGroupToGroup(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

    @Named("groupToGroupId")
    default Long groupToGroupId(Group group) {
        return group.getId();
    }
}
