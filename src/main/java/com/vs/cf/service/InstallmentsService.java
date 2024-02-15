package com.vs.cf.service;

import com.vs.cf.dto.InstallmentsDTO;
import com.vs.cf.entity.Installments;
import com.vs.cf.repository.InstallmentsRepository;
import com.vs.cf.viewdto.InstallmentsViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstallmentsService {

    @Autowired
    private InstallmentsRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<InstallmentsViewDTO> getAll() {
        return repository.findAll().stream().map(
                installments -> new InstallmentsViewDTO(
                        installments.getId(), installments.getOutputs(), installments.getInstallmentNumber(),
                        installments.getInstallmentDue()
                )
        ).collect(Collectors.toList());
    }

    public InstallmentsViewDTO getFindById(Long id) {
        Optional<Installments> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Installment not found.");
        }
        Installments installments = optional.get();
        return new InstallmentsViewDTO(
                installments.getId(), installments.getOutputs(), installments.getInstallmentNumber(),
                installments.getInstallmentDue()
        );
    }

    @Transactional
    public InstallmentsViewDTO save(InstallmentsDTO installments) {
        Installments instSave = mapper.map(installments, Installments.class);
        repository.save(instSave);
        return new InstallmentsViewDTO(
                installments.getId(), installments.getOutputs(), installments.getInstallmentNumber(),
                installments.getInstallmentDue()
        );
    }

    @Transactional
    public InstallmentsViewDTO update(InstallmentsDTO installments) {
        Installments outSave = mapper.map(installments, Installments.class);
        Optional<Installments> optional = repository.findById(installments.getId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Installment not found.");
        }
        repository.save(outSave);
        return new InstallmentsViewDTO(
                installments.getId(), installments.getOutputs(), installments.getInstallmentNumber(),
                installments.getInstallmentDue()
        );
    }

    @Transactional
    public String delete(Long id) {
        Optional<Installments> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Installment not found.");
        } else {
            repository.deleteById(id);
            return "Installment " + id + " removed successfully.";
        }
    }
}
