package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student {

	@Id @GeneratedValue
	@Column(name = "student_id")
	private String id;

	private String name;

	@Embedded
	private Address address;

	@ManyToOne
	@JoinColumn(name = "student_class_id")
	private Student_Class student_class;

	@OneToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;
}
