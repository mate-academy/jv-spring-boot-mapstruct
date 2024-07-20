package mate.academy.mapstruct.dto.student;

import lombok.Data;

import java.util.List;

public record CreateStudentRequestDto(
        String name,
        String email,
        Long groupId,
        List<Long> subjects
) {
}
