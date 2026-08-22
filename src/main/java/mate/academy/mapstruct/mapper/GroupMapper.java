package mate.academy.mapstruct.mapper;

import java.util.List;
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
    GroupDto toDto(Group group);

    @Mapping(target = "id", ignore = true)
    Group toModel(CreateGroupRequestDto requestDto);

    @Named("groupById")
    default Group groupById(Long id) {
        return Optional.ofNullable(id)
            .map(Group::new)
            .orElse(null);
    }

    @Named("idByGroup")
    default Long idByGroup(Group group) {
        return group != null ? group.getId() : null;
    }

    @Named("groupListByIds")
    default List<Group> groupListByIds(List<Long> ids) {
        return Optional.ofNullable(ids)
            .stream()
            .flatMap(List::stream)
            .map(Group::new)
            .toList();
    }

    @Named("idListByGroup")
    default List<Long> idListByGroup(List<Group> groups) {
        return groups.stream()
            .map(Group::getId)
            .toList();
    }
}
