package com.vs.cf.controller;

import com.vs.cf.dto.InputsDTO;
import com.vs.cf.service.InputsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inputs")
public class InputsController {

    @Autowired
    private InputsService service;

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
    public ResponseEntity save(@Valid @RequestBody InputsDTO inputs) {
        try {
            return new ResponseEntity<>(service.save(inputs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody InputsDTO inputs) {
        try {
            return new ResponseEntity<>(service.update(inputs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error deleting input.] - " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
