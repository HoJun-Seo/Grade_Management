package Student.Grade_Management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Student_Class {

	@Id @GeneratedValue
	@Column(name = "student_class_id")
	private Long id;

	@Column(nullable = false)
	private int number;
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "student_class")
	List<Student> studentList = new ArrayList<>();

	public Student_Class() {
	}

	public Student_Class(int number, String name) {
		this.number = number;
		this.name = name;
	}
}
