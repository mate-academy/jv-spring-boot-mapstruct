package mate.academy.mapstruct.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.group.CreateGroupRequestDto;
import mate.academy.mapstruct.dto.group.GroupDto;
import mate.academy.mapstruct.service.group.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupDto> findAll() {
        return groupService.findAll();
    }

    @PostMapping
    public GroupDto save(@RequestBody CreateGroupRequestDto requestDto) {
        return groupService.save(requestDto);
    }
}
