package Student.Grade_Management.service;

import Student.Grade_Management.domain.Address;
import Student.Grade_Management.domain.Student;
import Student.Grade_Management.domain.Student_Class;
import Student.Grade_Management.repository.StudentClassRepository;
import Student.Grade_Management.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentServiceTest {

	@Autowired StudentService studentService;
	@Autowired StudentRepository studentRepository;

	@Autowired StudentClassService studentClassService;
	@Autowired StudentClassRepository studentClassRepository;

	@Test
	@Rollback(value = false)
	public void 학생_분반_등록() throws Exception{
	    //given
		Student_Class student_class = new Student_Class(10,"1반");
	    //when
	    Long classId = studentClassService.create_class(student_class);
	    //then
		Assert.assertEquals(student_class, studentClassRepository.findOne(student_class.getId()).get(0));
	}

	@Test
	public void 분반_학생_찾기() throws Exception{
	    //given
		List<Student> students_list = studentClassService.findStudents_class("1반");


		//when

	    //then
		for (Student s : students_list){
			System.out.println(s.getName());
		}
	}

	@Test
	@Rollback(value = false)
	public void 학생등록() throws Exception{
	    //given
		Student student = new Student();
		student.setId("2014758058");
		student.setName("서호준");
		student.setAddress(new Address("부산", "사상로", "147"));

		Student_Class student_class = studentClassService.findByName("1반");
	    student.setStudent_class(student_class);
		//when
	    String saveId = studentService.join(student);
	    //then
		Assert.assertEquals(student, studentRepository.findById(saveId).get(0));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_학생_예외() throws Exception{
	    //given
		Student_Class student_class = new Student_Class(10,"1반");

	    Student student1 = new Student();
	    student1.setId("2014758058");
	    student1.setName("서호준");
	    student1.setAddress(new Address("부산", "사상로", "147"));
	    student1.setStudent_class(student_class);

	    Student student2 = new Student();
	    student2.setId("2014758058");
		student1.setName("서호준");
		student1.setAddress(new Address("부산", "사상로", "147"));
		student1.setStudent_class(student_class);

	    //when
	    studentService.join(student1);
	    studentService.join(student2);
	    //then

		Assert.fail("예외가 발생해야 한다.");
	}

}