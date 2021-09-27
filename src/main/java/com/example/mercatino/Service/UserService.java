package com.example.mercatino.Service;


import com.example.mercatino.Model.User;
import com.example.mercatino.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mercatino/user")
public class UserService {

    @Autowired
    RepositoryUser repositoryUser;

    @PostMapping("/adduser")
    private ResponseEntity<User> addNewUser(@RequestBody User user){
        return new ResponseEntity(repositoryUser.save(user), HttpStatus.OK);

    }

    @PutMapping("/updateuser/{id}")
    private ResponseEntity<User> updateUser(@RequestBody User user , @PathVariable Long id){
        if(repositoryUser.findById(id).isPresent()){
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            return new ResponseEntity(repositoryUser.save(newUser) , HttpStatus.OK);
        }
        return  new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getalluser")
    private ResponseEntity<User> getAllUser(){
        return new ResponseEntity(repositoryUser.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getuserinfo/{id}")
    private ResponseEntity<User> getUserInfo(@PathVariable("id")Long id){
        if(repositoryUser.findById(id).isPresent()){
            return new ResponseEntity(repositoryUser.findById(id) , HttpStatus.OK);
        }

        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteuser/{id}")
    private ResponseEntity<User> deleteUser(@PathVariable("id")Long id){
        if (repositoryUser.findById(id).isPresent()){
            repositoryUser.deleteById(id);
            return new ResponseEntity(null , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.NOT_FOUND);
    }
}
