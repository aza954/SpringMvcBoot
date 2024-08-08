package ru.aza954.springproject.SpringMvcBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "book")

public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "year")
    private int year;

    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "author")
    @NotBlank
    private String author;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Person owner;

    public Book(){

    }

    public Book( int year, String name, String author) {

        this.year = year;
        this.name = name;
        this.author = author;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id && year == book.year && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, year, name, author);
    }

    @Override
    public String toString() {
        return "Book{" +

                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", book_id=" + book_id +
                '}';
    }
}
