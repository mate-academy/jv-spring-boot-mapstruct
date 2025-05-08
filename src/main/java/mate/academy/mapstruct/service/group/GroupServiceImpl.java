package mate.academy.mapstruct.service.group;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.exception.EntityNotFoundException;
import mate.academy.mapstruct.mapper.GroupMapper;
import mate.academy.mapstruct.model.Group;
import mate.academy.mapstruct.repository.group.GroupRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Override
    public GroupDto save(CreateGroupRequestDto requestDto) {
        Group group = groupMapper.toModel(requestDto);
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public GroupDto findById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cant find group by id: " + id)
        );
        return groupMapper.toDto(group);
    }

    @Override
    public List<GroupDto> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::toDto)
                .toList();
    }
}
