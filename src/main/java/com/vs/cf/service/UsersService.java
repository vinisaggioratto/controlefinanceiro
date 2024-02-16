package com.vs.cf.service;

import com.vs.cf.dto.UsersDTO;
import com.vs.cf.entity.Users;
import com.vs.cf.repository.UsersRepository;
import com.vs.cf.viewdto.UsersViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vs.cf.security.WebSecurityConfig.passwordEncoder;

@Service
public class UsersService {

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    ;
    @Autowired
    private UsersRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<UsersViewDTO> getAll() {
        return repository.findAll().stream().map(
                users -> new UsersViewDTO(
                        users.getId(), users.getRegister(), users.getUsername(),
                        users.getUserUpdate(), users.getIs_active()
                )
        ).collect(Collectors.toList());
    }

    public UsersViewDTO getFindById(Long id) {
        Optional<Users> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("User not found.");
        }
        Users users = optional.get();
        return new UsersViewDTO(
                users.getId(), users.getRegister(), users.getUsername(),
                users.getUserUpdate(), users.getIs_active()
        );
    }

    @Transactional
    public UsersViewDTO save(UsersDTO users) {
        Users existUser = repository.findUserByUsername(users.getUsername());
        if (existUser != null) {
            throw new RuntimeException("User already exists!");
        }
        users.setPassword(passwordEncoder().encode(users.getPassword()));
        Users userSave = mapper.map(users, Users.class);
        repository.save(userSave);
        return new UsersViewDTO(
                users.getId(), users.getRegister(), users.getUsername(),
                users.getUserUpdate(), users.getIs_active()
        );
    }

    @Transactional
    public UsersViewDTO update(UsersDTO users) {
        users.setPassword(passwordEncoder().encode(users.getPassword()));
        Users userSave = mapper.map(users, Users.class);
        Optional<Users> optional = repository.findById(users.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("User not found.");
        }
        repository.save(userSave);
        return new UsersViewDTO(
                users.getId(), users.getRegister(), users.getUsername(),
                users.getUserUpdate(), users.getIs_active()
        );
    }

    @Transactional
    public void delete(Long id) {
        Optional<Users> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("User not found.");
        }
        Users users = optional.get();
        users.setIs_active(false);
        repository.save(users);
    }
}
