package mate.academy.mapstruct.dto.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentWithoutSubjectsDto {
    private Long id;
    private String name;
    private String email;
    private Long groupId;
}
