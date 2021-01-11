package Student.Grade_Management.service;

import Student.Grade_Management.domain.Student;
import Student.Grade_Management.domain.Student_Class;
import Student.Grade_Management.repository.StudentClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentClassService {

	private final StudentClassRepository studentClassRepository;

	@Transactional
	public Long create_class(Student_Class student_class){
		validateduplicateClass(student_class);
		studentClassRepository.create(student_class);
		return student_class.getId();
	}

	private void validateduplicateClass(Student_Class student_class) {
		List<Student_Class> student_classList = studentClassRepository.findOne(student_class.getId());
		if (!student_classList.isEmpty()){
			throw new IllegalStateException("이미 해당 분반이 존재하고 있습니다.");
		}

		try{ // 해당 이름의 분반이 존재하지 않을 경우 분반 생성
			Student_Class student_classList_name = studentClassRepository.findByName(student_class.getName());
			if (student_classList_name != null){
				throw new IllegalStateException("이미 해당 이름의 분반이 존재하고 있습니다.");
			}
		}
		catch (EmptyResultDataAccessException emptyResultDataAccessException){
		}
	}

	public Student_Class findByName(String class_name){
		return studentClassRepository.findByName(class_name);
	}

	public List<Student> findStudents_class(String class_name){
		return studentClassRepository.findStudents_class(class_name);
	}
}
