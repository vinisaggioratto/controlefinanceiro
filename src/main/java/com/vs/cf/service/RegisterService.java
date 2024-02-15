package com.vs.cf.service;

import com.vs.cf.dto.RegisterDTO;
import com.vs.cf.entity.Register;
import com.vs.cf.repository.RegisterRepository;
import com.vs.cf.viewdto.RegisterViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterService {


    @Autowired
    private RegisterRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<RegisterViewDTO> getAll() {
        return repository.findAll().stream().map(
                register -> new RegisterViewDTO(
                        register.getId(), register.getName(), register.getCpfCnpj(),
                        register.getBirthday(), register.getEmail(), register.getPhone(),
                        register.getIs_whatts(), register.getIs_active(),
                        register.getTypeRegister(), register.getRegisterUpdate()
                )
        ).collect(Collectors.toList());
    }

    public RegisterViewDTO getFindById(Long id) {
        Optional<Register> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Register not found.");
        }
        Register register = optional.get();
        return new RegisterViewDTO(
                register.getId(), register.getName(), register.getCpfCnpj(),
                register.getBirthday(), register.getEmail(), register.getPhone(),
                register.getIs_whatts(), register.getIs_active(),
                register.getTypeRegister(), register.getRegisterUpdate()
        );
    }

    @Transactional
    public RegisterViewDTO save(RegisterDTO register) {
        Register regSave = mapper.map(register, Register.class);
        repository.save(regSave);
        return new RegisterViewDTO(
                register.getId(), register.getName(), register.getCpfCnpj(),
                register.getBirthday(), register.getEmail(), register.getPhone(),
                register.getIs_whatts(), register.getIs_active(),
                register.getTypeRegister(), register.getRegisterUpdate()
        );
    }

    @Transactional
    public RegisterViewDTO update(RegisterDTO register) {
        Register regSave = mapper.map(register, Register.class);
        Optional<Register> optional = repository.findById(register.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Register not found.");
        }
        repository.save(regSave);
        return new RegisterViewDTO(
                register.getId(), register.getName(), register.getCpfCnpj(),
                register.getBirthday(), register.getEmail(), register.getPhone(),
                register.getIs_whatts(), register.getIs_active(),
                register.getTypeRegister(), register.getRegisterUpdate()
        );
    }

    @Transactional
    public void delete(Long id) {
        Optional<Register> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Register not found.");
        }
        Register register = optional.get();
        register.setIs_active(false);
        repository.save(register);
    }
}
