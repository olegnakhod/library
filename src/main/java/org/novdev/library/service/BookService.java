package org.novdev.library.service;

import java.util.List;
import java.util.Optional;

import org.novdev.library.domain.Book;
import org.novdev.library.domain.User;
import org.novdev.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private Logger logger = LoggerFactory.getLogger( BookService.class);
	
	@Autowired
	BookRepository bookRepository;
	
	public Book save(Book book) {
		logger.info("Save book item:" + book);
		return bookRepository.save(book);
	}
	
	public Book readById(Integer id) {
		logger.info("Find user by id:" + id);
		Book book = new Book();
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			book = optionalBook.get();
		}
		return book;
	}
	
	public void update(Book book, String authorName, String  bookName, Integer yearOfPublication , String publishingHouse) {
		logger.info("Update book:" +  book);
		if(!bookRepository.getById(book.getId()).equals(null)) {
			Book bookDB = bookRepository.getById(book.getId());
			bookDB.setAuthorName(authorName);
			bookDB.setBookName(bookName);
			bookDB.setYearOfPublication(yearOfPublication);
			bookDB.setPublishingHouse(publishingHouse);
			logger.info("Update user item:" + book + "to " + bookDB);
			bookRepository.save(bookDB);
		}else {
			throw new NullPointerException( book +  "this book don`t find in DB");
		}

	}
	
	public void delete(Book book) {
		logger.info("Delete  book item:" +  book);
		bookRepository.delete( book);
	}
	
	public List<Book> getAll(){
		logger.info("Get all books items");
		return bookRepository.findAll();
	}
	
	public List<Book> getAllByUserId(Integer userId){
		logger.info("Get all books items by user Id" + userId);
		return bookRepository.getAllByUserId(userId);
	}
	
	public void updateCopiesCount(Book book, Integer count) {
		logger.info("Update book:" +  book);
		if(!bookRepository.getById(book.getId()).equals(null)) {
			Book bookDB = bookRepository.getById(book.getId());
			bookDB.setNumberOfCopies(count);
			logger.info("Update user item:" + book + "to " + bookDB);
			bookRepository.save(bookDB);
		}else {
			throw new NullPointerException( book +  "this book don`t find in DB");
		}

	}
	
	public void updateUser(Book book, User user) {
		logger.info("Update book:" +  book);
		if(!bookRepository.getById(book.getId()).equals(null)) {
			Book bookDB = bookRepository.getById(book.getId());
			bookDB.setUser(user);
			logger.info("Update user item:" + book + "to " + bookDB);
			bookRepository.save(bookDB);
		}else {
			throw new NullPointerException( book +  "this book don`t find in DB");
		}

	}
	
	

}
