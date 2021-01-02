package Student.Grade_Management.repository;

import Student.Grade_Management.domain.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Member;
import java.util.List;

@Repository
public class StudentRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Student student){
		em.persist(student);
	}

	public Student findOne(String id){ // 특정 학번으로 학생 검색
		return em.find(Student.class, id);
	}

	public List<Student> findAll(){
		return em.createQuery("select s from Student s", Student.class)
				.getResultList();
	}


}
