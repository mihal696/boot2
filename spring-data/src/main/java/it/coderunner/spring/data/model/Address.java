package it.coderunner.spring.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7810138207278403020L;

	@Id
	@GeneratedValue
	private Long addid;
	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String number;

	public Address(String street, String number) {
		this.street = street;
		this.number = number;

	}
}
