package Student.Grade_Management.repository;

import Student.Grade_Management.domain.Grade;
import Student.Grade_Management.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentGradeRepository {

	private final EntityManager em;

	public void save(Grade grade){
		em.persist(grade);
	}

	public List<Student> findByUpperGrade(int score, String score_name){
		if (score_name == "korean_score"){
			return em.createQuery("select s from Student s where s.grade.korean >= :score order by s.grade.korean desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "math_score"){
			return em.createQuery("select s from Student s where s.grade.math >= :score order by s.grade.math desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "english_score"){
			return em.createQuery("select s from Student s where s.grade.english >= :score order by s.grade.english desc")
					.setParameter("score", score)
					.getResultList();
		}
		else {
			throw new IllegalStateException("과목 이름이 잘못 되었습니다.");
		}
	}

	public List<Student> findByLowerGrade(int score, String score_name){
		if (score_name == "korean_score"){
			return em.createQuery("select s from Student s where s.grade.korean < :score order by s.grade.korean desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "math_score"){
			return em.createQuery("select s from Student s where s.grade.math < :score order by s.grade.math desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "english_score"){
			return em.createQuery("select s from Student s where s.grade.english < :score order by s.grade.english desc ")
					.setParameter("score", score)
					.getResultList();
		}
		else {
			throw new IllegalStateException("과목 이름이 잘못 되었습니다.");
		}
	}

	public List<Student> findAllAverage(){
		return em.createQuery("select s from Student s order by s.grade.avg desc")
				.getResultList();
	}

	public List<Student> findByUpperAverage(double average){
		return em.createQuery("select s from Student s where s.grade.avg >= :average order by s.grade.avg desc")
				.setParameter("average", average)
				.getResultList();
	}

	public List<Student> findByLowerAverage(double average){
		return em.createQuery("select s from Student s where s.grade.avg < :average order by s.grade.avg desc")
				.setParameter("average", average)
				.getResultList();
	}


}
