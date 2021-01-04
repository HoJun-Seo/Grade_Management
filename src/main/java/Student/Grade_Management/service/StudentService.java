package Student.Grade_Management.service;

import Student.Grade_Management.domain.Student;
import Student.Grade_Management.domain.Student_Class;
import Student.Grade_Management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class StudentService {

	private final StudentRepository studentRepository;

	@Transactional
	public String join(Student student, Student_Class student_class){
		validateDuplicateStudent(student);
		studentRepository.save(student, student_class);
		return student.getId();
	}

	private void validateDuplicateStudent(Student student) {
		List<Student> findStudents = studentRepository.findById(student.getId());
		if (!findStudents.isEmpty()){
			throw new IllegalStateException("이미 해당 학번의 학생이 존재하고 있습니다.");
		}
	}

	public List<Student> findStudents(){
		return studentRepository.findAll();
	}

	public List<Student> findOne(String studentId){
		return studentRepository.findById(studentId);
	}
}
