package com.bookstore.repository;

import com.bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vitalii on 05.10.2017.
 */
public interface BookRepository extends CrudRepository<Book, Long>{
    List<Book> findByTitleContaining(String keyword);

}
