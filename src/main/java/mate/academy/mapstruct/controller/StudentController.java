package mate.academy.mapstruct.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.student.CreateStudentRequestDto;
import mate.academy.mapstruct.dto.student.StudentDto;
import mate.academy.mapstruct.dto.student.StudentWithoutSubjectsDto;
import mate.academy.mapstruct.service.student.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentWithoutSubjectsDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public StudentDto save(@RequestBody CreateStudentRequestDto requestDto) {
        return studentService.save(requestDto);
    }
}
