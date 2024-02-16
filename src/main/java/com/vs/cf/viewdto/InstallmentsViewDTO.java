package com.vs.cf.viewdto;

import com.vs.cf.entity.Outputs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstallmentsViewDTO {

    private Long id;
    private String outputs;
    private Integer installmentNumber;
    private BigDecimal value;
    private Date installmentDue;

    public InstallmentsViewDTO(Long id, Outputs outputs, Integer installmentNumber,
                               BigDecimal value, Date installmentDue) {
        this.id = id;
        this.outputs = outputs.getDescription();
        this.installmentNumber = installmentNumber;
        this.value = value;
        this.installmentDue = installmentDue;
    }
}
