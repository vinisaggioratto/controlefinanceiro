package com.vs.cf.service;

import com.vs.cf.dto.OutputsDTO;
import com.vs.cf.entity.Outputs;
import com.vs.cf.repository.OutputsRepository;
import com.vs.cf.viewdto.OutputsViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OutputsService {

    @Autowired
    private OutputsRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<OutputsViewDTO> getAll() {
        return repository.findAll().stream().map(
                outputs -> new OutputsViewDTO(
                        outputs.getId(), outputs.getRegister(), outputs.getDescription(), outputs.getValue(),
                        outputs.getFinalValue(), outputs.getFormOfPayment(), outputs.getInstallments(),
                        outputs.getDaysBetweenInstallments(), outputs.getPurchaseDate(),
                        outputs.getNotes(), outputs.getStatusPayment()
                )
        ).collect(Collectors.toList());
    }

    public OutputsViewDTO getFindById(Long id) {
        Optional<Outputs> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Output not found.");
        }
        Outputs outputs = optional.get();
        return new OutputsViewDTO(
                outputs.getId(), outputs.getRegister(), outputs.getDescription(), outputs.getValue(),
                outputs.getFinalValue(), outputs.getFormOfPayment(), outputs.getInstallments(),
                outputs.getDaysBetweenInstallments(), outputs.getPurchaseDate(),
                outputs.getNotes(), outputs.getStatusPayment()
        );
    }

    @Transactional
    public OutputsViewDTO save(OutputsDTO outputs) {
        Outputs outSave = mapper.map(outputs, Outputs.class);
        repository.save(outSave);
        return new OutputsViewDTO(
                outputs.getId(), outputs.getRegister(), outputs.getDescription(), outputs.getValue(),
                outputs.getFinalValue(), outputs.getFormOfPayment(), outputs.getInstallments(),
                outputs.getDaysBetweenInstallments(), outputs.getPurchaseDate(),
                outputs.getNotes(), outputs.getStatusPayment()
        );
    }

    @Transactional
    public OutputsViewDTO update(OutputsDTO outputs) {
        Outputs outSave = mapper.map(outputs, Outputs.class);
        Optional<Outputs> optional = repository.findById(outputs.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Output not found.");
        }
        repository.save(outSave);
        return new OutputsViewDTO(
                outputs.getId(), outputs.getRegister(), outputs.getDescription(), outputs.getValue(),
                outputs.getFinalValue(), outputs.getFormOfPayment(), outputs.getInstallments(),
                outputs.getDaysBetweenInstallments(), outputs.getPurchaseDate(),
                outputs.getNotes(), outputs.getStatusPayment()
        );
    }

    @Transactional
    public String delete(Long id) {
        Optional<Outputs> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Output not found.");
        }else {
            repository.deleteById(id);
            return "Output "+ id +" removed successfully.";
        }
    }
}
