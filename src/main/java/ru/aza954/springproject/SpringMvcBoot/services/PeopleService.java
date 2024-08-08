package ru.aza954.springproject.SpringMvcBoot.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aza954.springproject.SpringMvcBoot.models.Book;
import ru.aza954.springproject.SpringMvcBoot.models.Person;
import ru.aza954.springproject.SpringMvcBoot.repositories.PeopleRepository;
import ru.aza954.springproject.SpringMvcBoot.repositories.PeopleRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }



    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Person person){
        person.setCreatedAt(new Date());
        Calendar cal = Calendar.getInstance();
        Date date = new Date();

        person.setAge((int)((date.getTime()-person.getYearofbirth().getTime())/31536000000L));
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        updatedPerson.setUser_id(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    @Transactional
    public List<Book> findBooks(int id){
        Optional<Person> foundperson = peopleRepository.findById(id);
        Person person = foundperson.orElse(null);
        Hibernate.initialize(person.getBooks());
        return person.getBooks();
    }
}
