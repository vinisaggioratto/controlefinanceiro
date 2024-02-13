package com.vs.cf.viewdto;

import com.vs.cf.entity.Outputs;
import com.vs.cf.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstallmentsViewDTO {

    private Long id;
    private String outputs;
    private Integer installmentNumber;
    private Timestamp installmentDue;

    public InstallmentsViewDTO(Long id, Outputs outputs, Integer installmentNumber,
                               Timestamp installmentDue) {
        this.id = id;
        this.outputs = outputs.getDescription();
        this.installmentNumber = installmentNumber;
        this.installmentDue = installmentDue;
    }
}
