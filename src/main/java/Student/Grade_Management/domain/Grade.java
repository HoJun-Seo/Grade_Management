package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Grade {

	@Id @GeneratedValue
	@Column(name = "grade_id")
	private Long id;

	private int korean;
	private int math;
	private int english;

	@OneToOne(mappedBy = "grade")
	private Student student;


}
