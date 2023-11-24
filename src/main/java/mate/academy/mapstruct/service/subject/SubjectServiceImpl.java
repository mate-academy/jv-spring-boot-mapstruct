package mate.academy.mapstruct.service.subject;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.model.Subject;
import mate.academy.mapstruct.dto.subject.CreateSubjectRequestDto;
import mate.academy.mapstruct.dto.subject.SubjectDto;
import mate.academy.mapstruct.mapper.SubjectMapper;
import mate.academy.mapstruct.repository.subject.SubjectRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectDto save(CreateSubjectRequestDto requestDto) {
        Subject subject = subjectMapper.toModel(requestDto);
        return subjectMapper.toDto(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toDto)
                .toList();
    }
}
