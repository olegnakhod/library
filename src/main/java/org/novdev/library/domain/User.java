package org.novdev.library.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String surname;
	
	@Column(name = "birth_year")
	@NotNull
	private Integer birthYear;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@OneToMany(mappedBy = "user")
	private List<Book> books = new ArrayList<>();
	
	public User(Integer id, @NotNull String name, @NotNull String surname, @NotNull Integer birthYear) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthYear = birthYear;
	}
	
	public User(@NotNull String name, @NotNull String surname, @NotNull Integer birthYear) {
		this.name = name;
		this.surname = surname;
		this.birthYear = birthYear;
	}

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public List<Book> getBooks() {
		return books;
	}


	@Override
	public int hashCode() {
		return Objects.hash(birthYear, cardNumber, id, name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthYear, other.birthYear) && Objects.equals(cardNumber, other.cardNumber)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birthYear=" + birthYear
				+ ", cardNumber=" + cardNumber + "]";
	}

}
