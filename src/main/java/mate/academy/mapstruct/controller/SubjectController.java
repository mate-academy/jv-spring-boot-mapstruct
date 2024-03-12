//package mate.academy.mapstruct.controller;
//
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
//import mate.academy.mapstruct.dto.subject.SubjectDto;
//import mate.academy.mapstruct.service.subject.SubjectService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/subjects")
//public class SubjectController {
//
//    private final SubjectService subjectService;
//
//    @GetMapping
//    public List<SubjectDto> findAll() {
//        return subjectService.findAll();
//    }
//
//    @PostMapping
//    public SubjectDto save(@RequestBody CreateSubjectRequestDto requestDto) {
//        return subjectService.save(requestDto);
//    }
//}
