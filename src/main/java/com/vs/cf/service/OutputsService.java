package com.vs.cf.service;

import com.vs.cf.dto.OutputsDTO;
import com.vs.cf.entity.Installments;
import com.vs.cf.entity.Outputs;
import com.vs.cf.repository.OutputsRepository;
import com.vs.cf.viewdto.OutputsViewDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutputsService {

    private final OutputsRepository repository;
    private final InstallmentsService installmentsService;

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
        Outputs newOutputs = new Outputs();
        newOutputs.setRegister(outputs.getRegister());
        newOutputs.setDescription(outputs.getDescription());
        newOutputs.setValue(outputs.getValue());
        newOutputs.setFinalValue(outputs.getFinalValue());
        newOutputs.setFormOfPayment(outputs.getFormOfPayment());
        newOutputs.setInstallments(outputs.getInstallments());
        newOutputs.setDaysBetweenInstallments(outputs.getDaysBetweenInstallments());
        newOutputs.setPurchaseDate(outputs.getPurchaseDate());
        newOutputs.setNotes(outputs.getNotes());
        newOutputs.setStatusPayment(outputs.getStatusPayment());
        newOutputs.setUsers(outputs.getUsers());
        repository.save(newOutputs);

        for (int i = 1; i <= outputs.getInstallments(); i++) {
            Double installmentsNumber = Double.valueOf(newOutputs.getInstallments());
            Double valueOutputs = Double.valueOf(String.valueOf(newOutputs.getValue()));
            Double instValueConvert = valueOutputs / installmentsNumber;
            BigDecimal installmentsValue = BigDecimal.valueOf(instValueConvert);
            Installments installments = new Installments();
            installments.setOutputs(newOutputs);
            installments.setInstallmentNumber(i);
            installments.setValue(installmentsValue);
            installments.setInstallmentDue(
                    calcularVencimentoParcela(outputs.getPurchaseDate(), (outputs.getDaysBetweenInstallments() * i)));
            installments.setUsers(newOutputs.getUsers());
            installmentsService.saveManual(installments);
        }

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
        } else {
            repository.deleteById(id);
            return "Output " + id + " removed successfully.";
        }
    }

    public static Date calcularVencimentoParcela(Date dataCompra, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataCompra);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }
}
