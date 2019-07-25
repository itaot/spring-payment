package me.itaot.payment.keepaccount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAccountController {


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity keepAcct(){

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity reverse(){

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
