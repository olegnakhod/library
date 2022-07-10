package org.novdev.library.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "autor_name")
	private String authorName;
	
	@Column(name =  "book_name")
	private String bookName;
	
	@Column(name = "year_of_publication")
	private Integer yearOfPublication;
	
	@Column(name = "publishing_house")
	private String 	publishingHouse;
	
	@Column(name = "book_is_free")
	private boolean bookIsFree;
	
	@Column(name = "number_of_copies")
	private Integer numberOfCopies;
	
	@ManyToOne
	@JoinColumn(name = "user_fk_id",nullable = false)
	private User user;

	
	public Book(Integer id, String authorName, String bookName, Integer yearOfPublication, String publishingHouse,
			boolean bookIsFree, Integer numberOfCopies) {
		this.id = id;
		this.authorName = authorName;
		this.bookName = bookName;
		this.yearOfPublication = yearOfPublication;
		this.publishingHouse = publishingHouse;
		this.bookIsFree = bookIsFree;
		this.numberOfCopies = numberOfCopies;
	}

	public Book(String authorName, String bookName, Integer yearOfPublication, String publishingHouse,
			boolean bookIsFree, Integer numberOfCopies) {
		this.authorName = authorName;
		this.bookName = bookName;
		this.yearOfPublication = yearOfPublication;
		this.publishingHouse = publishingHouse;
		this.bookIsFree = bookIsFree;
		this.numberOfCopies = numberOfCopies;
	}

	public Book() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(Integer yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public boolean getBookIsFree() {
		return bookIsFree;
	}

	public void setBookIsFree(boolean bookIsFree) {
		this.bookIsFree = bookIsFree;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookIsFree, bookName, id, numberOfCopies, publishingHouse, yearOfPublication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorName, other.authorName) && bookIsFree == other.bookIsFree
				&& Objects.equals(bookName, other.bookName) && Objects.equals(id, other.id)
				&& Objects.equals(numberOfCopies, other.numberOfCopies)
				&& Objects.equals(publishingHouse, other.publishingHouse)
				&& Objects.equals(yearOfPublication, other.yearOfPublication);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", authorName=" + authorName + ", bookName=" + bookName + ", yearOfPublication="
				+ yearOfPublication + ", publishingHouse=" + publishingHouse + ", bookIsFree=" + bookIsFree
				+ ", numberOfCopies=" + numberOfCopies + "]";
	}
	
}
