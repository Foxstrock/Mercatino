package com.example.mercatino.Service;

import com.example.mercatino.Model.Books;
import com.example.mercatino.Model.User;
import com.example.mercatino.repository.RepositoryBooks;
import com.example.mercatino.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.awt.print.Book;
import java.util.Optional;

@RestController
@RequestMapping("/mercatino/books")
public class BooksService {

    @Autowired
    RepositoryBooks repositoryBooks;

    @Autowired
    RepositoryUser repositoryUser;

    @PostMapping("/addbook/{id}")
    private ResponseEntity<Books> addNewBook(@RequestBody Books book , @PathVariable("id")Long id){
        User user = repositoryUser.getById(id);
        if(repositoryUser.findById(id).isPresent()){
            user.getBookList().add(book);
            return new ResponseEntity(repositoryUser.save(user) , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @PutMapping("updatebook/{id}")
    private ResponseEntity<Books> updateBooks(@RequestBody Books book,@PathVariable("id")Long id){
        if(repositoryBooks.findById(id).isPresent()){
            Books newbook = repositoryBooks.findById(id).get();

            newbook.setTitle(book.getTitle());
            newbook.setSubject(book.getSubject());
            newbook.setSchool(book.getSchool());
            newbook.setPublisher(book.getPublisher());
            newbook.setPrice(book.getPrice());
            newbook.setIsbn(book.getIsbn());
            newbook.setQuantity(book.getQuantity());
            return  new ResponseEntity(repositoryBooks.save(newbook) , HttpStatus.OK);
        }

        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallbook")
    private ResponseEntity<Books> getAllBooks(){
        return new ResponseEntity(repositoryBooks.findAll() , HttpStatus.OK);
    }

    @GetMapping("/getbookinfo/{id}")
    private ResponseEntity<Books> getBookInfo(@PathVariable("id")Long id){
        if(repositoryBooks.findById(id).isPresent()){
            return new ResponseEntity(repositoryBooks.findById(id) , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchbytitle/{title}")
    private ResponseEntity<Books> getBookByTitle(@PathVariable("title")String title){
        if(repositoryBooks.findByTitle(title).isPresent()){
            return new ResponseEntity(repositoryBooks.findByTitle(title) , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchbyisbn/{isbn}")
    private ResponseEntity<Books> getBookByIsbn(@PathVariable("isbn")String isbn){
        if(repositoryBooks.findByIsbn(isbn).isPresent()){
            return new ResponseEntity(repositoryBooks.findByIsbn(isbn) , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deletebook")
    private ResponseEntity<Books> deleteBooks(@RequestParam Long idUser , @RequestParam Long idBook){
        Optional<Books> checkBook = repositoryBooks.findById(idBook);
        if (checkBook.isPresent()){
            Books book = checkBook.get();

            User user = repositoryUser.findById(idUser).get();
            user.getBookList().remove(book);
            repositoryUser.save(user);

            repositoryBooks.deleteById(idBook);
            return new ResponseEntity(null , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }
}
