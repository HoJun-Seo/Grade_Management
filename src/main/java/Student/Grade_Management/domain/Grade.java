package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Grade {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grade_id")
	private Long id;

	@Column(name = "korean_score", nullable = false)
	private int korean;
	@Column(name = "math_score", nullable = false)
	private int math;
	@Column(name = "english_score", nullable = false)
	private int english;

	@Column(name = "average")
	private double avg;

	@OneToOne(mappedBy = "grade", fetch = FetchType.LAZY)
	private Student student;

	public Grade() {
	}
}
