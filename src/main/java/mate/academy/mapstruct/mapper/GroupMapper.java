package mate.academy.mapstruct.mapper;

import java.util.Optional;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = mate.academy.mapstruct.config.MapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    @Named("getGroupById")
    default Group getGroupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }
}
