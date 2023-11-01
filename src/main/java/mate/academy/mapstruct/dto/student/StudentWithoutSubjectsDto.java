package mate.academy.mapstruct.dto.student;

import lombok.Data;

@Data
public class StudentWithoutSubjectsDto {
    private Long id;
    private String name;
    private String email;
    private Long groupId;
}
