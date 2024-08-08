package ru.aza954.springproject.SpringMvcBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.aza954.springproject.SpringMvcBoot.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

}
