package com.example.mercatino.Model;


import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import javax.persistence.*;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(required = false , hidden = true)
    Long idBook;

    String title;

    String publisher;

    String isbn;

    String subject;

    String school;

    Double price;

    Long Quantity;


    public Books(Long idBook, String title, String publisher, String isbn, String subject, String school, Double price, Long quantity, List<User> userList) {
        this.idBook = idBook;
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.subject = subject;
        this.school = school;
        this.price = price;
        Quantity = quantity;
    }

    public Long getQuantity() {
        return Quantity;
    }

    public void setQuantity(Long quantity) {
        Quantity = quantity;
    }

    public Books() {
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
