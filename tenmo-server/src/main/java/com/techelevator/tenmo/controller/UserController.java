package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.ForbiddenException;
import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private UserDao dao;

    public UserController(UserDao userDao){
        this.dao = userDao;
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/users")
    public List<User> list(@RequestParam(defaultValue = "") String username){
        if(username.equalsIgnoreCase("")){
            return dao.findAll();
        }
        return null;
    }

    //I should be able to choose from a list of users to send TE Bucks to.
//    @GetMapping(path = "/users/{username}")
//    public int selectUser(@PathVariable String username, Principal principal) throws UserNotFoundException,
//            ForbiddenException {
//
//        //
//        if (dao.findIdByUsername(principal.getName()) != transaction.getFromUserId()) {
//            throw new ForbiddenException();
//        }
//
//        if(dao.findByUsername(username) == null) {
//            throw new UserNotFoundException();
//        }
//
//        return dao.findIdByUsername(principal.getName());
//    }
}
