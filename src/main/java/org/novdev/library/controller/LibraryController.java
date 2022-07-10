package org.novdev.library.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.novdev.library.domain.Book;
import org.novdev.library.domain.User;
import org.novdev.library.service.BookService;
import org.novdev.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
	
	private Logger logger = LoggerFactory.getLogger( LibraryController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
    private void postConstruct() {
		User library = new User(1,"library", "library", 2022); 
		if( !(userService.readById(1).equals(library ))) {
	        userService.save(library );
		}
    }
	
	public void giveBook(User user, Book book) {
		if(book.getNumberOfCopies() > 0 && book.getBookIsFree()) {
			bookService.updateCopiesCount(book, (book.getNumberOfCopies() - 1));
			bookService.updateUser(book, user);
		}else {
			logger.info("Library dosent have this book" + book);
		}
	}
	
	public void takeBook(User user, Book book) {
		User library  = userService.readById(1);
		Book bookDB = bookService.readById(book.getId());
		bookService.updateCopiesCount(bookDB, (bookDB.getNumberOfCopies() + 1));
		bookService.updateUser(bookDB, library);
	}
	
	public void showAllUserWithThisBooks() {
		List<User> users = userService.getAll();
		for(User user:users) {
			logger.info(user + " he has this book: \n");
			List<Book> books = bookService.getAllByUserId(user.getId());
			for(Book book: books ) {
				logger.info(book + "\n");
			}
		}
	}
	
}
