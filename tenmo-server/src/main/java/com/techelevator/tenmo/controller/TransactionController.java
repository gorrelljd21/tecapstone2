package com.techelevator.tenmo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class TransactionController {

    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public Transfer addTransaction(@RequestBody @Valid Transfer transfer){

    }

}
