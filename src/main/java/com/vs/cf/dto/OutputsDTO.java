package com.vs.cf.dto;

import com.vs.cf.entity.Register;
import com.vs.cf.entity.Users;
import com.vs.cf.enums.FormOfPayment;
import com.vs.cf.enums.StatusPayment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputsDTO {

    private Long id;
    private Register register;
    @NotBlank(message = "Informe uma descrição válida.")
    private String description;
    private BigDecimal value;
    private BigDecimal finalValue;
    private FormOfPayment formOfPayment;
    private Integer installments;
    private Integer daysBetweenInstallments;
    private Date purchaseDate;
    private String notes;
    private Users users;
    private StatusPayment statusPayment;
}
