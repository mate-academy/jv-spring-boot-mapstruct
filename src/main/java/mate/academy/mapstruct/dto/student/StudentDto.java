package mate.academy.mapstruct.dto.student;

import java.util.List;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private Long groupId;
    private List<Long> subjectIds;
}
