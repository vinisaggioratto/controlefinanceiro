package com.vs.cf.controller;

import com.vs.cf.dto.UsersDTO;
import com.vs.cf.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getFindById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getFindById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody UsersDTO users) {
        try {
            return new ResponseEntity<>(service.save(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody UsersDTO users) {
        try {
            return new ResponseEntity<>(service.update(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        try {
            return new ResponseEntity<>("User removed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error deleting user.] - " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
