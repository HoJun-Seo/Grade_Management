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

import javax.persistence.NoResultException;
import java.util.List;

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

	@Test
	public void 성적_전체검색() throws Exception{
	    //given
		List<Grade> gradeList = studentGradeService.findAll();
		Student student = studentService.findOne("2014758058");
	    //when
	    Grade grade = gradeList.get(0);
	    //then
		assertEquals(grade.getStudent().getId(), student.getId());
	}
	
	@Test
	public void 과목별_상위성적_검색() throws Exception {
		//given
		List<Integer> gradeList = studentGradeService.findByUpperGrade(90, "english_score");
		//when
		int english_grade = gradeList.get(0);
		//then
		Student student = studentService.findOne("2014758058");
		assertEquals(english_grade, student.getGrade().getEnglish());
	}
	@Test(expected = NoResultException.class)
	public void 과목별_상위성적_검색에러() throws Exception{
	    //given
		List<Integer> gradeList = studentGradeService.findByUpperGrade(100, "english_score");
	    //when
		if (gradeList.isEmpty())
			throw new NoResultException();
	    //then
		fail("리스트가 비어있어야 한다.");
	}
	@Test(expected = NoResultException.class)
	public void 과목별_상위성적_과목에러() throws Exception{
	    //given
		List<Integer> gradeList = studentGradeService.findByUpperGrade(100, "non_exist_subject");
	    //when
		if (gradeList.isEmpty())
			throw new NoResultException();
	    //then
		fail("일치하는 과목명이 없어 에러가 발생해야 한다.");
	}
	
	@Test
	public void 과목별_하위성적_검색() throws Exception{
	    //given
		List<Integer> gradeList = studentGradeService.findByLowerGrade(100, "korean_score");
	    //when
	    int korean_grade = gradeList.get(0);
	    Student student = studentService.findOne("2014758058");
	    //then
		assertEquals(korean_grade, student.getGrade().getKorean());
	}
	@Test(expected = NoResultException.class)
	public void 과목별_하위성적_검색에러() throws Exception{
	    //given
		List<Integer> gradeList = studentGradeService.findByLowerGrade(90, "korean_score");
	    //when
	    if (gradeList.isEmpty())
	    	throw new NoResultException();
	    //then
		fail("리스트가 비어있어야 한다.");
	}
	@Test(expected = NoResultException.class)
	public void 과목별_하위성적_과목에러() throws Exception{
	    //given
		List<Integer> gradeList = studentGradeService.findByLowerGrade(100, "non_exist_subject");
	    //when
		if (gradeList.isEmpty())
			throw new NoResultException();
	    //then
		fail("일치하는 과목명이 없어 에러가 발생해야 한다.");
	}
	
	@Test
	public void 평균상위_검색() throws Exception{
	    //given
	    List<Double> upperAverage_list = studentGradeService.findByUpperAverage(90.0);
	    //when
	    double upperAverage = upperAverage_list.get(0);
	    Student student = studentService.findOne("2014758058");
	    //then
		if (upperAverage == student.getGrade().getAvg()){
			System.out.println("테스트를 정상적으로 통과하였습니다.");
		}
		else{
			fail("테스트에 실패하였습니다.");
		}
	}
	@Test(expected = NoResultException.class)
	public void 평균상위_검색에러() throws Exception{
	    //given
		List<Double> upperAverage_list = studentGradeService.findByUpperAverage(100.0);
	    //when
	    if (upperAverage_list.isEmpty())
	    	throw new NoResultException();
	    //then
		fail("리스트가 비어있어야 한다.");
	}
	
	@Test
	public void 평균하위_검색() throws Exception{
	    //given
	    List<Double> lowerAverage_list = studentGradeService.findBuLowerAverage(100.0);
	    //when
	    double lowerAverage = lowerAverage_list.get(0);
	    Student student = studentService.findOne("2014758058");
	    //then
		if (lowerAverage == student.getGrade().getAvg()) {
			System.out.println("테스트를 정상적으로 통과하였습니다.");
		}else {
			fail("테스트에 실패하였습니다.");
		}
	}
	@Test(expected = NoResultException.class)
	public void 평균하위_검색에러() throws Exception{
	    //given
		List<Double> lowerAverage_list = studentGradeService.findBuLowerAverage(80.0);
	    //when
		if (lowerAverage_list.isEmpty())
			throw new NoResultException();
	    //then
		fail("리스트가 비어있어야 한다.");
	}
}