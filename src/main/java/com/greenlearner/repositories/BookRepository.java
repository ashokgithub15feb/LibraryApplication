package com.greenlearner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenlearner.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	//@Query("update Book u set u.name=?2 where u.id=?1")
   // int updateAddress(long id, String name);
}
