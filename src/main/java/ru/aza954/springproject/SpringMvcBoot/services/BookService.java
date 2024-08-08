package ru.aza954.springproject.SpringMvcBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aza954.springproject.SpringMvcBoot.models.Book;
import ru.aza954.springproject.SpringMvcBoot.models.Person;
import ru.aza954.springproject.SpringMvcBoot.repositories.BookRepository;
import ru.aza954.springproject.SpringMvcBoot.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;
    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findByBookName(String name){
        return bookRepository.findByNameStartingWith(name);
    }

    public List<Book> findAllPage(int page,int books_per_page,String sort){

        if (sort.toLowerCase().equals("true")){
            return bookRepository.findAll(PageRequest.of(page,books_per_page, Sort.by("year"))).getContent();
        }
        else {
            return bookRepository.findAll(PageRequest.of(page,books_per_page)).getContent();
        }
    }

    public List<Book> findAll(String sort){
        if (sort.toLowerCase().equals("true")){
            return bookRepository.findAll(Sort.by("year"));

        }else {
        return bookRepository.findAll();
        }
    }
    public Book findOne(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id,Book updatedBook){
        updatedBook.setBook_id(id);
        bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    @Transactional
    public List<Book> checkBook(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        Book book = foundBook.orElse(null);
        List<Book> books = new ArrayList<>();
        if (book.getOwner() == null){
            books.add(book);
            return books;
        }
        else {


            return books;
        }
    }
    @Transactional
    public List<Person> getPerson(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        Book book = foundBook.orElse(null);
        List<Person> people = new ArrayList<>();
        people.add(book.getOwner());
        return people;
    }
    @Transactional
    public void setBook(int bookId,int personId){
        Optional<Book> foundBook = bookRepository.findById(bookId);
        Optional<Person> foundPerson = peopleRepository.findById(personId);
        Person person = foundPerson.orElse(null);
        Book book = foundBook.orElse(null);
        book.setOwner(person);
        person.getBooks().add(book);
    }

    @Transactional
    public void takeBook(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        Book book = foundBook.orElse(null);
        Optional<Person> foundPerson = peopleRepository.findById(book.getOwner().getUser_id());
        Person person = foundPerson.orElse(null);
        System.out.println("овнер " + book.getOwner());
        System.out.println("книги чела " + person.getBooks());
        book.setOwner(null);
        person.getBooks().remove(book);
        peopleRepository.save(person);
        bookRepository.save(book);
        System.out.println("овнер " + book.getOwner());
        System.out.println("книги чела " + person.getBooks());
    }

//    @jakarta.transaction.Transactional
//    public void takeBook(int id){
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class,id);
//        Person person = session.get(Person.class,book.getOwner().getUser_id());
//        book.setOwner(null);
//        person.getBooks().remove(book);
//
//
//    }

//    @jakarta.transaction.Transactional
//    public void setBook(int bookId,int personId){
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class,bookId);
//        Person person = session.get(Person.class,personId);
//        book.setOwner(person);
//        person.getBooks().add(book);
////        Person person = session.get(Person.class,personId);
////        Query query = session.createQuery("UPDATE Book SET owner = :person WHERE book_id = :bookId");
////        query.setParameter("person", person);
////        query.setParameter("bookId", bookId);
////        query.executeUpdate();
//
//    }

//    public List<Person> getPerson(int id){
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class,id);
//        List<Person> people = new ArrayList<>();
//        people.add(book.getOwner());
//        return people;
//    }

//    public List<Book> checkBook(int id){
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select b from Book b where book_id=id and owner is null ", Book.class).getResultList();
//    }


}
