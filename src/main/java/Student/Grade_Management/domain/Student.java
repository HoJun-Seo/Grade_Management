package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student {

	@Id
	@Column(name = "student_id", nullable = false)
	private String id;

	@Column(nullable = false)
	private String name;

	@Embedded
	@Column(nullable = false)
	private Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_class_id", nullable = false)
	private Student_Class student_class;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id", nullable = false)
	private Grade grade;

	public Student() {
	}

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
