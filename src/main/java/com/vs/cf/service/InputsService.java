package com.vs.cf.service;

import com.vs.cf.dto.InputsDTO;
import com.vs.cf.entity.Inputs;
import com.vs.cf.repository.InputsRepository;
import com.vs.cf.viewdto.InputsViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InputsService {

    @Autowired
    private InputsRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<InputsViewDTO> getAll() {
        return repository.findAll().stream().map(
                inputs -> new InputsViewDTO(
                        inputs.getId(), inputs.getRegister(), inputs.getDescription(), inputs.getValue(),
                        inputs.getDate(), inputs.getStatusPayment(), inputs.getNotes()
                )
        ).collect(Collectors.toList());
    }

    public InputsViewDTO getFindById(Long id) {
        Optional<Inputs> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Input not found.");
        }
        Inputs inputs = optional.get();
        return new InputsViewDTO(
                inputs.getId(), inputs.getRegister(), inputs.getDescription(), inputs.getValue(),
                inputs.getDate(), inputs.getStatusPayment(), inputs.getNotes()
        );
    }

    @Transactional
    public InputsViewDTO save(InputsDTO inputs) {
        Inputs inpSave = mapper.map(inputs, Inputs.class);
        repository.save(inpSave);
        return new InputsViewDTO(
                inputs.getId(), inputs.getRegister(), inputs.getDescription(), inputs.getValue(),
                inputs.getDate(), inputs.getStatusPayment(), inputs.getNotes()
        );
    }

    @Transactional
    public InputsViewDTO update(InputsDTO inputs) {
        Inputs inpSave = mapper.map(inputs, Inputs.class);
        Optional<Inputs> optional = repository.findById(inputs.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Input not found.");
        }
        repository.save(inpSave);
        return new InputsViewDTO(
                inputs.getId(), inputs.getRegister(), inputs.getDescription(), inputs.getValue(),
                inputs.getDate(), inputs.getStatusPayment(), inputs.getNotes()
        );
    }

    @Transactional
    public String delete(Long id) {
        Optional<Inputs> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Input not found.");
        } else {
            repository.deleteById(id);
            return "Input " + id + " removed successfully.";
        }
    }
}
