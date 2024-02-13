package com.vs.cf.viewdto;

import com.vs.cf.entity.Register;
import com.vs.cf.enums.FormOfPayment;
import com.vs.cf.enums.StatusPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputsViewDTO {

    private Long id;
    private String register;
    private String description;
    private BigDecimal value;
    private BigDecimal finalValue;
    private String formOfPayment;
    private Integer installments;
    private Integer daysBetweenInstallments;
    private Timestamp purchaseDate;
    private String notes;
    private String statusPayment;

    public OutputsViewDTO(Long id, Register register, String description,
                          BigDecimal value, BigDecimal finalValue, FormOfPayment formOfPayment,
                          Integer installments, Integer daysBetweenInstallments,
                          Timestamp purchaseDate, String notes, StatusPayment statusPayment) {
        this.id = id;
        this.register = register.getName();
        this.description = description;
        this.value = value;
        this.finalValue = finalValue;
        this.formOfPayment = formOfPayment.toString();
        this.installments = installments;
        this.daysBetweenInstallments = daysBetweenInstallments;
        this.purchaseDate = purchaseDate;
        this.notes = notes;
        this.statusPayment = statusPayment.toString();
    }
}
