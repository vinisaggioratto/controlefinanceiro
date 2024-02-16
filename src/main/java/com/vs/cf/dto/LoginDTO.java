package com.vs.cf.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Campo login é obrigatório.")
    private String username;
    @NotBlank(message = "Campo password é obrigatório.")
    private String password;
}
