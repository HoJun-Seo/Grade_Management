package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Grade {

	@Id @GeneratedValue
	@Column(name = "grade_id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private int korean;
	@Column(nullable = false)
	private int math;
	@Column(nullable = false)
	private int english;

	@OneToOne(mappedBy = "grade", fetch = FetchType.LAZY)
	private Student student;

	public Grade() {
	}
}
