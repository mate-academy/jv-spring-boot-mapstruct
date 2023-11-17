package mate.academy.mapstruct.mapper;

import java.util.Optional;
import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface GroupMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    GroupDto toDto(Group group);

    @Mapping(source = "name", target = "name")
    Group toModel(CreateGroupRequestDto requestDto);

    @Named("getGroupById")
    default Group getGroupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }
}
