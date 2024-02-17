package com.vs.cf.service;

import com.vs.cf.Utils.CurrentDate;
import com.vs.cf.dto.UsersDTO;
import com.vs.cf.entity.Users;
import com.vs.cf.repository.RegisterRepository;
import com.vs.cf.repository.UsersRepository;
import com.vs.cf.viewdto.UsersViewDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final RegisterService registerService;

    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    };

    private ModelMapper mapper = new ModelMapper();

    public List<UsersViewDTO> getAll() {
        return repository.findAll().stream().map(
                users -> new UsersViewDTO(
                        users.getId(), users.getRegister().getName(), users.getUsername(),
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
                users.getId(), users.getRegister().getName(), users.getUsername(),
                users.getUserUpdate(), users.getIs_active()
        );
    }

    @Transactional
    public UsersViewDTO save(UsersDTO users) {
        Users existUser = repository.findUserByUsername(users.getUsername());
        if (existUser != null) {
            throw new RuntimeException("User already exists!");
        }
        Users userSave = new Users();
        userSave.setId(null);
        userSave.setRegister(registerService.getRegisterFindByName(users.getRegister()));
        userSave.setUsername(users.getUsername());
        userSave.setPassword(passwordEncoder().encode(users.getPassword()));
        userSave.setUserUpdate(CurrentDate.getCurrentTimestamp());
        userSave.setIs_active(users.getIs_active());

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
