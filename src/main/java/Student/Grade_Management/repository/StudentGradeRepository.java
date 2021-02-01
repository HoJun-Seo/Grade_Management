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

	public Grade findGrade(String studentId){
		return em.createQuery("select g from Grade g where g.student.id =:studentId", Grade.class)
				.setParameter("studentId", studentId)
				.getSingleResult();
	}

	public List<Grade> findByUpperGrade(int score, String score_name){
		if (score_name == "korean_score"){
			return em.createQuery("select g from Grade g where g.korean >= :score order by g.korean desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "math_score"){
			return em.createQuery("select g from Grade g where g.math >= :score order by g.math desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "english_score"){
			return em.createQuery("select g from Grade g where g.english >= :score order by g.english desc")
					.setParameter("score", score)
					.getResultList();
		}
		else {
			throw new IllegalStateException("과목 이름이 잘못 되었습니다.");
		}
	}

	public List<Grade> findByLowerGrade(int score, String score_name){
		if (score_name == "korean_score"){
			return em.createQuery("select g from Grade g where g.korean < :score order by g.korean desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "math_score"){
			return em.createQuery("select g from Grade g where g.math < :score order by g.math desc")
					.setParameter("score", score)
					.getResultList();
		}
		else if (score_name == "english_score"){
			return em.createQuery("select g from Grade g where g.english < :score order by g.english desc")
					.setParameter("score", score)
					.getResultList();
		}
		else {
			throw new IllegalStateException("과목 이름이 잘못 되었습니다.");
		}
	}

	public List<Grade> findAll(){
		return em.createQuery("select g from Grade g order by g.avg desc")
				.getResultList();
	}

	public List<Grade> findByUpperAverage(double average){
		return em.createQuery("select g from Grade g where g.avg >= :average order by g.avg desc")
				.setParameter("average", average)
				.getResultList();
	}

	public List<Grade> findByLowerAverage(double average){
		return em.createQuery("select g from Grade g where g.avg < :average order by g.avg desc")
				.setParameter("average", average)
				.getResultList();
	}
}
