package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class UserController {

    private UserDao dao;

    public UserController(UserDao userDao){
        this.dao = userDao;
    }

    //See the list of users
    @GetMapping(path = "/users")
    public List<User> list(@RequestParam(defaultValue = "") String username){
        if(username.equalsIgnoreCase("")){
            return dao.findAll();
        }
        return null;
    }

    //I should be able to choose from a list of users to send TE Bucks to.
    @GetMapping(path = "users/{username}")
    public int selectUser(@PathVariable String username) throws UserNotFoundException {
        return dao.findIdByUsername(username);
    }


}
