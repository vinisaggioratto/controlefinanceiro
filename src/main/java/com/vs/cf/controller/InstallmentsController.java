package com.vs.cf.controller;

import com.vs.cf.dto.InstallmentsDTO;
import com.vs.cf.dto.OutputsDTO;
import com.vs.cf.entity.Installments;
import com.vs.cf.service.InstallmentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installments")
public class InstallmentsController {

    @Autowired
    private InstallmentsService service;

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
    public ResponseEntity save(@Valid @RequestBody InstallmentsDTO installments) {
        try {
            return new ResponseEntity<>(service.save(installments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody InstallmentsDTO installments) {
        try {
            return new ResponseEntity<>(service.update(installments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error] - " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("[Error deleting installment.] - " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
