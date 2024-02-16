package com.vs.cf.controller;

import com.vs.cf.dto.LoginDTO;
import com.vs.cf.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService service;

    //@PreAuthorize("hasRole('PRODUCT_INSERT')")
    @PostMapping
    public ResponseEntity login(@Valid @RequestBody LoginDTO login){
        try {
            return new ResponseEntity<>(service.validarUser(login), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("[Error validating login.] - Controller: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
