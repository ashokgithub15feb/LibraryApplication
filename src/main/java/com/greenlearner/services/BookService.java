package com.greenlearner.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.greenlearner.models.Book;
import com.greenlearner.repositories.BookRepository;

@Service
public class BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	@Autowired
	private BookRepository bookRepository;
	
	public Book addBook(Book book) {
		logger.info(String.format("adding book with id - %s", book.getId()));
		return bookRepository.save(book);
	}

	@CachePut(cacheNames = "books", key = "#id")
	public Book updateBook(Book book, long id) {
		
		Optional<Book> bookOptional = bookRepository.findById(id);
		if (bookOptional.isPresent()) {
			Book getBookFromDB = bookOptional.get();
			getBookFromDB.setName(book.getName());
			bookRepository.save(getBookFromDB);
		} else {
			return new Book();
		}
		logger.info(String.format("book updated with new name - %s", book.getId()));
		return book;
	}

	@Cacheable(cacheNames = "books", key = "#id")
	public Book getBook(long id) {
		
		logger.info(String.format("fetching book from db - %s", id));
		
		Optional<Book> book = bookRepository.findById(id);
		
		
		if (book.isPresent()) {
			return book.get();
		} else {
			return new Book();
		}
	}

	@CacheEvict(cacheNames  = "books", key = "#id")
	public String deleteBook(long id) {
		bookRepository.deleteById(id);
		return "Book deleted";
	}
}
