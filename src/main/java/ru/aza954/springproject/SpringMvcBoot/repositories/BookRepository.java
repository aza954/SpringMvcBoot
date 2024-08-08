package ru.aza954.springproject.SpringMvcBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aza954.springproject.SpringMvcBoot.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByName(String name);
    List<Book> findByNameStartingWith(String startname);
}
