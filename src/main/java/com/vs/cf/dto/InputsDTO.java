package com.vs.cf.dto;

import com.vs.cf.entity.Register;
import com.vs.cf.entity.Users;
import com.vs.cf.enums.StatusPayment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputsDTO {

    private Long id;
    private Register register;
    @NotBlank(message = "Informe uma descrição válida.")
    private String description;
    private Users users;
    private BigDecimal value;
    private Timestamp date;
    private StatusPayment statusPayment;
    private String notes;
}
