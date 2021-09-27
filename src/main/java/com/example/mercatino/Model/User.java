package com.example.mercatino.Model;

import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(required = false , hidden = true)
    Long idUser;

    String name;

    String surname;

    @ManyToMany(cascade = CascadeType.ALL)
    @ApiModelProperty(required = false , hidden = true)
    List<Books> bookList=new ArrayList<>();

    public User() {
    }

    public User(Long idUser, String name, String surname, List<Books> bookList) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.bookList = bookList;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Books> getBookList() {
        return bookList;
    }

    public void setBookList(List<Books> bookList) {
        this.bookList = bookList;
    }
}
