package mate.academy.mapstruct.mapper;

import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    GroupDto toDto(Group group);

    Group toModel(CreateGroupRequestDto requestDto);
}
