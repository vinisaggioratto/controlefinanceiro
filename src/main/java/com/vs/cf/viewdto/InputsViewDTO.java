package com.vs.cf.viewdto;

import com.vs.cf.entity.Register;
import com.vs.cf.enums.StatusPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputsViewDTO {

    private Long id;
    private String register;
    private String description;
    private BigDecimal value;
    private Timestamp date;
    private String statusPayment;
    private String notes;

    public InputsViewDTO(Long id, Register register, String description,
                         BigDecimal value, Timestamp date,
                         StatusPayment statusPayment, String notes) {
        this.id = id;
        this.register = register.getName();
        this.description = description;
        this.value = value;
        this.date = date;
        this.statusPayment = statusPayment.toString();
        this.notes = notes;
    }
}
