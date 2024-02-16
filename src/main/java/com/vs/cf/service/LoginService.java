package com.vs.cf.service;

import com.vs.cf.dto.LoginDTO;
import com.vs.cf.entity.Users;
import com.vs.cf.repository.LoginRepository;
import com.vs.cf.security.WebSecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    private ModelMapper mapper = new ModelMapper();

    public Boolean validarUser(LoginDTO login) {
        Boolean loginStatus = false;
        Users users = loginRepository.findUserByUsername(login.getUsername());
        if (users != null && WebSecurityConfig.passwordEncoder().matches(login.getPassword(), users.getPassword())) {
            loginStatus = true;
            return loginStatus;
        } else {
            return loginStatus;
        }
    }
}
