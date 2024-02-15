package com.vs.cf.dto;

import com.vs.cf.enums.TypeRegister;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {

    private Long id;
    @NotBlank(message = "Informe um nome válido.")
    private String name;
    @NotBlank(message = "Informe um cpf/cnpj válido.")
    private String cpfCnpj;
    private Timestamp birthday;
    @Email
    @NotBlank(message = "Informe um email válido.")
    private String email;
    @NotBlank(message = "Informe um telefone válido.")
    private String phone;
    private Boolean is_whatts;
    private Boolean is_active;
    private Timestamp registerUpdate;
    private TypeRegister typeRegister;
}
