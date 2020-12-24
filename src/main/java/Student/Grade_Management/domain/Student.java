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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_class_id")
	private Student_Class student_class;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id")
	private Grade grade;

	// 연관관계 편의 메소드
	public void setStudent_class(Student_Class student_class){
		this.student_class = student_class;
		student_class.getStudentList().add(this);
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
		grade.setStudent(this);
	}
}
