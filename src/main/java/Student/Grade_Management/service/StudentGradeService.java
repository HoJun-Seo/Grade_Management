package Student.Grade_Management.service;

import Student.Grade_Management.domain.Grade;
import Student.Grade_Management.domain.Student;
import Student.Grade_Management.repository.StudentGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
		grade.setAvg((grade.getEnglish()+grade.getKorean()+grade.getMath())/3);
		studentGradeRepository.save(grade);
		return grade.getId();
	}

	public Grade findOne(Student student){
		Grade student_grade = studentGradeRepository.findGrade(student.getId());
		return student_grade;
	}

	public List<Grade> findAll(){
		List<Grade> studentgrade_list = studentGradeRepository.findAll();
		return studentgrade_list;
	}
}
