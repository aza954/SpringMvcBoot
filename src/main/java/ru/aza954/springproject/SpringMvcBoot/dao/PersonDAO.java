package ru.aza954.springproject.SpringMvcBoot.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aza954.springproject.SpringMvcBoot.models.Book;
import ru.aza954.springproject.SpringMvcBoot.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Transactional
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();


    }
    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);


    }
    @Transactional
    public void newPerson(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);

    }
    @Transactional
    public List<Book> getBooks(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        System.out.println(person.getBooks());
        return person.getBooks();
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        session.remove(person);


    }

    @Transactional
    public void update(int id, Person updatedPerson){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        person.setFullname(updatedPerson.getFullname());
        person.setYearofbirth(updatedPerson.getYearofbirth());




    }

}
