package ru.aza954.springproject.SpringMvcBoot.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aza954.springproject.SpringMvcBoot.models.Book;
import ru.aza954.springproject.SpringMvcBoot.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();

    }
    @Transactional
    public Book show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id);


    }
    @Transactional
    public void newBook(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);

    }
    @Transactional
    public List<Book> checkBook(int id){
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select b from Book b where book_id=id and owner is null ", Book.class).getResultList();
    }
    @Transactional
    public void setBook(int bookId,int personId){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,bookId);
        Person person = session.get(Person.class,personId);
        book.setOwner(person);
        person.getBooks().add(book);
//        Person person = session.get(Person.class,personId);
//        Query query = session.createQuery("UPDATE Book SET owner = :person WHERE book_id = :bookId");
//        query.setParameter("person", person);
//        query.setParameter("bookId", bookId);
//        query.executeUpdate();

    }
    @Transactional
    public List<Person> getPerson(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        List<Person> people = new ArrayList<>();
        people.add(book.getOwner());
        return people;
    }
    @Transactional
    public void takeBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        Person person = session.get(Person.class,book.getOwner().getUser_id());
        book.setOwner(null);
        person.getBooks().remove(book);


    }
    @Transactional
    public void updateBook(Book book,int id){
        Session session = sessionFactory.getCurrentSession();
        Book booktobeupdate = session.get(Book.class,id);
        booktobeupdate.setAuthor(book.getAuthor());
        booktobeupdate.setName(book.getName());
        booktobeupdate.setYear(book.getYear());



    }
    @Transactional
    public void deleteBook(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        session.remove(book);
    }


}
