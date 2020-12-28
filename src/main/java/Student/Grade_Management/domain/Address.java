package Student.Grade_Management.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String zipcode;

	public Address() {
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
