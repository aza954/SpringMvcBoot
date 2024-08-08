package ru.aza954.springproject.SpringMvcBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "fullname")
    @NotBlank
    private String fullname;

    @Column(name = "yearofbirth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date yearofbirth;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;
    @Column(name = "age")
    private int age=0;

    public Person(){


    }
    public Person(String fullname) {

        this.fullname = fullname;


    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getYearofbirth() {
        return yearofbirth;
    }

    public void setYearofbirth(Date yearofbirth) {
        this.yearofbirth = yearofbirth;
    }

    public List<Book> getBooks() {
        if (this.books == null){
            this.books = new ArrayList<>();
        }
        return books;
    }

    public void setBooks(List<Book> books) {

    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user_id=" + user_id +
                ", fullname='" + fullname + '\'' +
                ", yearofbirth=" + yearofbirth +
                '}';
    }
}
