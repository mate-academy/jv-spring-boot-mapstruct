package mate.academy.mapstruct.mapper;

import java.util.Optional;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
                .map(Group::new)
                .orElse(null);
    }

    @Named("groupGetId")
    default Long groupGetId(Group group) {
        return group.getId();
    }
}
