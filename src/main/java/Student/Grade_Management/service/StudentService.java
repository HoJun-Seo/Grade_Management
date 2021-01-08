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
	public String join(Student student){
		validateDuplicateStudent(student);
		studentRepository.save(student);
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

	public Student findOne(String studentId){
		return studentRepository.findById(studentId).get(0);
	}

	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}
}
