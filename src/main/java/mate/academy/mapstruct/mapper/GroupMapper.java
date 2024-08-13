package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.config.MapperConfig;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

//@Mapper(config = MapperConfig.class)
@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface GroupMapper {
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);

    //created
    @Named("getById")
    default Group getById(Long id) {
        if (id != null) {
            return new Group(id);
        }
        return null;
    }
}
