package Student.Grade_Management.service;

import Student.Grade_Management.domain.Grade;
import Student.Grade_Management.repository.StudentGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentGradeService {

	private final StudentGradeRepository studentGradeRepository;

	@Transactional
	public Long grade_input(Grade grade){
		studentGradeRepository.save(grade);
		return grade.getId();
	}
}
