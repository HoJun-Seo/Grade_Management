package Student.Grade_Management.repository;

import Student.Grade_Management.domain.Student;
import Student.Grade_Management.domain.Student_Class;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Student student){
		em.persist(student);
	}

	public List<Student> findById(String id){ // 특정 학번으로 학생 검색
		// 학번 유일성 검증을 위해 List 타입으로 데이터를 받는다.
		return em.createQuery("select s from Student s where s.id = :id", Student.class)
				.setParameter("id", id)
				.getResultList();
	}

	public List<Student> findAll(){
		return em.createQuery("select s from Student s", Student.class)
				.getResultList();
	}

	public List<Student> findByName(String name){
		return em.createQuery("select s from Student s where s.name = :name", Student.class)
				.setParameter("name", name)
				.getResultList();
	}


}
