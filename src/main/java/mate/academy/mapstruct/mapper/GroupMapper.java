package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "id", source = "id") // Map the ID if needed
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);
}
