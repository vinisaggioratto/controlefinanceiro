package com.vs.cf.dto;

import com.vs.cf.entity.Outputs;
import com.vs.cf.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentsDTO {

    private Long id;
    private Outputs outputs;
    private Integer installmentNumber;
    private Timestamp installmentDue;
    private Users users;
}
