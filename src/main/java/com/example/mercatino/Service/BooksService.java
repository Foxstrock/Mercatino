package com.example.mercatino.Service;

import com.example.mercatino.Model.Books;
import com.example.mercatino.Model.User;
import com.example.mercatino.repository.RepositoryBooks;
import com.example.mercatino.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercatino/books")
public class BooksService {

    @Autowired
    RepositoryBooks repositoryBooks;

    @Autowired
    RepositoryUser repositoryUser;

    @PostMapping("/addbook/{id}")
    private ResponseEntity<Books> addNewBook(@RequestBody Books book , @PathVariable("id")Long id){
        if(repositoryUser.findById(id).isPresent()){
            User user = repositoryUser.getById(id);
            user.getBookList().clear();
            user.getBookList().add(book);
            return new ResponseEntity(repositoryUser.save(user) , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @PutMapping("updatebook/{id}")
    private ResponseEntity<Books> updateBooks(@RequestBody Books book,@PathVariable("id")Long id){
        if(repositoryBooks.findById(id).isPresent()){
            Books newbook = new Books();

            newbook.setTitle(book.getTitle());
            newbook.setSubject(book.getSubject());
            newbook.setSchool(book.getSchool());
            newbook.setPublisher(book.getPublisher());
            newbook.setPrice(book.getPrice());
            newbook.setIsbn(book.getIsbn());
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

    @DeleteMapping("/deletebook/{id}")
    private ResponseEntity<Books> deleteBooks(@PathVariable("id")Long id){
        if (repositoryBooks.findById(id).isPresent()){
            repositoryBooks.deleteById(id);
            return new ResponseEntity(null , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }
}
