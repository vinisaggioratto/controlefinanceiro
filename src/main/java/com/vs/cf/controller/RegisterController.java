package com.vs.cf.controller;

import com.vs.cf.dto.RegisterDTO;
import com.vs.cf.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error - ]" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getFindById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getFindById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error - ]" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody RegisterDTO register) {
        try {
            return new ResponseEntity<>(service.save(register), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody RegisterDTO register) {
        try {
            return new ResponseEntity<>(service.update(register), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error - ]" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        try {
            return new ResponseEntity<>("Register removed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error deleting register.] - " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
