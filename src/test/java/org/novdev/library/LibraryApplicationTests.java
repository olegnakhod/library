package org.novdev.library;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.novdev.library.controller.LibraryController;
import org.novdev.library.domain.Book;
import org.novdev.library.domain.User;
import org.novdev.library.repository.BookRepository;
import org.novdev.library.repository.UserRepository;
import org.novdev.library.service.BookService;
import org.novdev.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ComponentScan(basePackages = {"org.novdev.library"})
public class LibraryApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LibraryController controller;
	
	
	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);

		userService.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(2));

		User userFromDb = users.get(1);
		assertTrue(userFromDb.getName().equals(user.getName()));
		assertTrue(userFromDb.getSurname().equals(user.getSurname()));
		assertTrue(userFromDb.getBirthYear().equals(user.getBirthYear()));
	}
	
	@Test
	public void testReadById() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(2));

		User getById = userService.readById(user.getId());

		assertTrue(getById.getName().equals(user.getName()));
		assertTrue(getById.getSurname().equals(user.getSurname()));
		assertTrue(getById.getBirthYear().equals(user.getBirthYear()));
	}
	
	@Test
	public void testUpdateUser(){
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(1));


		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);


		userRepository.save(user);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(2));
		
		User userSecond = new User();
		userSecond.setName("Polja");
		userSecond.setSurname("Nakhod");
		userSecond.setBirthYear(2016);

		userService.update(user,userSecond.getName(),userSecond.getSurname(), userSecond.getBirthYear());
		
		users = userRepository.findAll();
		user = users.get(1);
		
		assertTrue(user.getName().equals(userSecond.getName()));
		assertTrue(user.getSurname().equals(userSecond.getSurname()));
		assertTrue(user.getBirthYear().equals(userSecond.getBirthYear()));
	}
	
	@Test
	public void testDeleteUser(){
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(1));


		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		User userSecond = new User();
		userSecond.setName("Polja");
		userSecond.setSurname("Nakhod");
		userSecond.setBirthYear(2016);


		userRepository.save(user);
		userRepository.save(userSecond);
		
		users = userRepository.findAll();
		assertThat(users, hasSize(3));
		
		userService.delete(userSecond);;
		
		users = userRepository.findAll();
		userSecond = users.get(1);
		
		assertTrue(user.getName().equals(userSecond.getName()));
		assertTrue(user.getSurname().equals(userSecond.getSurname()));
		assertTrue(user.getBirthYear().equals(userSecond.getBirthYear()));
	}
	
	@Test
	public void testSaveBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		List<Book> books = bookRepository.findAll();
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);

		bookService.save(book);;
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(1));

		Book bookFromDB = books.get(0);
		
		assertTrue(bookFromDB.getAuthorName().equals(book.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(book.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(book.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies().equals(book.getNumberOfCopies()));
		assertTrue(bookFromDB.getYearOfPublication().equals(book.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(book.getBookIsFree()));
	}
	
	@Test
	public void testReadByIdBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		List<Book> books = bookRepository.findAll();
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);

		bookService.save(book);;
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(1));

		Book bookFromDB = bookService.readById(book.getId());
		
		assertTrue(bookFromDB.getAuthorName().equals(book.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(book.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(book.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies().equals(book.getNumberOfCopies()));
		assertTrue(bookFromDB.getYearOfPublication().equals(book.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(book.getBookIsFree()));
	}
	
	@Test
	public void testUpdateBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		List<Book> books = bookRepository.findAll();
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(1);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(user);

		bookService.update(book, bookSecond.getAuthorName(),bookSecond.getBookName(),
						   bookSecond.getYearOfPublication(), bookSecond.getPublishingHouse());
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(1));

		Book bookFromDB = books.get(0);
		
		assertTrue(bookFromDB.getAuthorName().equals(bookSecond.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(bookSecond.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(bookSecond.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies().equals(bookSecond.getNumberOfCopies()));
		assertTrue(bookFromDB.getYearOfPublication().equals(bookSecond.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(bookSecond.getBookIsFree()));
	}
	
	@Test
	public void testUpdateCopiesCountBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		List<Book> books = bookRepository.findAll();
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(2);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(user);

		bookService.updateCopiesCount(book, bookSecond.getNumberOfCopies());
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(1));

		Book bookFromDB = books.get(0);
		
		assertTrue(bookFromDB.getAuthorName().equals(book.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(book.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(book.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies()==(bookSecond.getNumberOfCopies()));
		assertTrue(bookFromDB.getYearOfPublication().equals(book.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(book.getBookIsFree()));
	}
	
	
	
	
	@Test
	public void testDeleteBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		List<Book> books = bookRepository.findAll();
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(1);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(user);
		
		bookService.save(bookSecond);
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(2));
		
		bookService.delete(bookSecond);
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(1));

		Book bookFromDB = books.get(0);
		
		assertTrue(bookFromDB.getAuthorName().equals(book.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(book.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(book.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies().equals(book.getNumberOfCopies()));
		assertTrue(bookFromDB.getYearOfPublication().equals(book.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(book.getBookIsFree()));
	}
	
	@Test
	public void testGetAllByUserIDBook() {
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		User userSecond = new User();
		userSecond.setName("Polja");
		userSecond.setSurname("Nakhod");
		userSecond.setBirthYear(2016);
		
		userService.save(userSecond);
		
		List<Book> books = bookRepository.findAll();
		
		assertThat(books, hasSize(0));

		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(user);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(1);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(user);
		
		bookService.save(bookSecond);
		
		Book bookThird = new Book();
		bookThird.setAuthorName("George Martin");
		bookThird.setBookName("Winter is coming");
		bookThird.setPublishingHouse("ABabaGalamha");
		bookThird.setNumberOfCopies(1);
		bookThird.setYearOfPublication(2020);
		bookThird.setBookIsFree(true);
		bookThird.setUser(userSecond);
		
		bookService.save(bookThird);
		
		books = bookRepository.findAll();
		assertThat(books, hasSize(3));
		
		List<Book> booksByUserId = bookService.getAllByUserId(user.getId());
		assertThat(booksByUserId, hasSize(2));
	}
	
	@Test
	public void testGiveBook() {
		
		User library = userService.readById(1);
		
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(library);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(1);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(library);
		
		bookService.save(bookSecond);
		
		Book bookThird = new Book();
		bookThird.setAuthorName("George Martin");
		bookThird.setBookName("Winter is coming");
		bookThird.setPublishingHouse("ABabaGalamha");
		bookThird.setNumberOfCopies(1);
		bookThird.setYearOfPublication(2020);
		bookThird.setBookIsFree(true);
		bookThird.setUser(library);
		
		bookService.save(bookThird);
		
		controller.giveBook(user, book);
		
		List<Book> booksByUserId = bookService.getAllByUserId(user.getId());
		assertThat(booksByUserId, hasSize(1));
		
		List<Book> booksOfLibrary = bookService.getAllByUserId(library.getId());
		assertThat(booksOfLibrary, hasSize(2));
		
		Book bookFromDB = booksByUserId.get(0);
		
		assertTrue(bookFromDB.getAuthorName().equals(book.getAuthorName()));
		assertTrue(bookFromDB.getBookName().equals(book.getBookName()));
		assertTrue(bookFromDB.getPublishingHouse().equals(book.getPublishingHouse()));
		assertTrue(bookFromDB.getNumberOfCopies()== 0);
		assertTrue(bookFromDB.getYearOfPublication().equals(book.getYearOfPublication()));
		assertTrue(bookFromDB.getBookIsFree()==(book.getBookIsFree()));	
	}
	
	@Test
	public void testTakeBook() {
		
		User library = userService.readById(1);
		
		User user = new User();
		user.setName("Oleg");
		user.setSurname("Nakhod");
		user.setBirthYear(1);
		
		userService.save(user);
		
		Book book = new Book();
		book.setAuthorName("Jack London");
		book.setBookName("Martin Iden");
		book.setPublishingHouse("ABabaGalamha");
		book.setNumberOfCopies(1);
		book.setYearOfPublication(2017);
		book.setBookIsFree(true);
		book.setUser(library);
		
		bookService.save(book);
		
		Book bookSecond = new Book();
		bookSecond.setAuthorName("George Martin");
		bookSecond.setBookName("A song ice and fire");
		bookSecond.setPublishingHouse("ABabaGalamha");
		bookSecond.setNumberOfCopies(1);
		bookSecond.setYearOfPublication(2021);
		bookSecond.setBookIsFree(true);
		bookSecond.setUser(library);
		
		bookService.save(bookSecond);
		
		Book bookThird = new Book();
		bookThird.setAuthorName("George Martin");
		bookThird.setBookName("Winter is coming");
		bookThird.setPublishingHouse("ABabaGalamha");
		bookThird.setNumberOfCopies(1);
		bookThird.setYearOfPublication(2020);
		bookThird.setBookIsFree(true);
		bookThird.setUser(library);
		
		bookService.save(bookThird);
		
		controller.giveBook(user, book);
		
		List<Book> booksByUserId = bookService.getAllByUserId(user.getId());
		assertThat(booksByUserId, hasSize(1));
		
		List<Book> booksOfLibrary = bookService.getAllByUserId(library.getId());
		assertThat(booksOfLibrary, hasSize(2));
		
		Book bookFromDB = booksByUserId.get(0);
		
		assertTrue(bookFromDB.getNumberOfCopies()== 0);

		controller.takeBook(user, book);
		
		booksByUserId = bookService.getAllByUserId(user.getId());
		assertThat(booksByUserId, hasSize(0));
		
		booksOfLibrary = bookService.getAllByUserId(library.getId());
		assertThat(booksOfLibrary, hasSize(3));		
	}
}
