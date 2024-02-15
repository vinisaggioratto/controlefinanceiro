package com.vs.cf.dto;

import com.vs.cf.entity.Register;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Long id;
    private Register register;
    @NotBlank(message = "Informe um nome de usuário válido.")
    private String username;
    @NotBlank(message = "Informe uma senha válida.")
    private String password;
    private Timestamp userUpdate;
    private Boolean is_active;
}
