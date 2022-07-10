package org.novdev.library.repository;

import java.util.List;

import org.novdev.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT b FROM Book b WHERE b.user.id =:id")
	List<Book> getAllByUserId(Integer id);
}
