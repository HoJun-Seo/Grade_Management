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

	private int number;
	private String name;

	@OneToMany(mappedBy = "student_class")
	List<Student> studentList = new ArrayList<>();
}
