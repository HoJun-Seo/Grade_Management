package Student.Grade_Management.service;

import Student.Grade_Management.domain.Grade;
import Student.Grade_Management.domain.Student;
import Student.Grade_Management.repository.StudentGradeRepository;
import Student.Grade_Management.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentGradeServiceTest {

	@Autowired StudentService studentService;
	@Autowired StudentRepository studentRepository;

	@Autowired StudentGradeService studentGradeService;
	@Autowired StudentGradeRepository studentGradeRepository;

	@Test
	@Rollback(value = false)
	public void 성적_등록() throws Exception{
	    //given
		Student student = studentService.findOne("2014758058");
		Grade grade = new Grade();
		grade.setEnglish(90);
		grade.setKorean(100);
		grade.setMath(80);
		student.setGrade(grade);

	    //when
		Long gradId = studentGradeService.grade_input(grade);
	    //then
		assertEquals(grade, studentGradeService.findOne(student));
	}
}