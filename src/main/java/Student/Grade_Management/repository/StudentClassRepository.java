package Student.Grade_Management.repository;

import Student.Grade_Management.domain.Student_Class;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentClassRepository {

	@PersistenceContext
	private EntityManager em;

	public void create(Student_Class student_class){
		em.persist(student_class);
	}

	public List<Student_Class> findOne(Long id){ // 분반 생성시 유효성 검증을 위해 생성
		return em.createQuery("select sc from Student_Class sc where sc.id =:id", Student_Class.class)
				.setParameter("id", id)
				.getResultList();
	}

	public List<Student_Class> findAll(){
		return em.createQuery("select sc from Student_Class sc", Student_Class.class)
				.getResultList();
	}

	public Student_Class findByName(String class_name){
		return em.createQuery("select sc from Student_Class sc where sc.name = :class_name", Student_Class.class)
				.setParameter("class_name", class_name)
				.getSingleResult();
	}
}
