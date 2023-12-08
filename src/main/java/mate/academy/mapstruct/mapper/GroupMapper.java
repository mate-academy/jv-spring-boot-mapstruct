package mate.academy.mapstruct.mapper;

import java.util.Optional;
import mate.academy.mapstruct.dto.config.MapperConfig;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new).orElse(null);
    }

    @Named("getIdFromGroup")
    default Long getIdFromGroup(Group group) {
        return Optional.ofNullable(group.getId()).orElse(null);
    }
}
