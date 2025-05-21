package mate.academy.mapstruct.mapper.impl;

import javax.annotation.processing.Generated;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.mapper.GroupMapper;
import mate.academy.mapstruct.model.Group;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T14:42:43+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupDto toDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDto groupDto = new GroupDto();

        if ( group.getId() != null ) {
            groupDto.setId( group.getId() );
        }
        if ( group.getName() != null ) {
            groupDto.setName( group.getName() );
        }

        return groupDto;
    }

    @Override
    public Group toModel(CreateGroupRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Group group = new Group();

        if ( requestDto.name() != null ) {
            group.setName( requestDto.name() );
        }

        return group;
    }
}
