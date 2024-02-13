package com.vs.cf.viewdto;

import com.vs.cf.enums.TypeRegister;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterViewDTO {

    private Long id;
    private String name;
    private String cpfCnpj;
    private Timestamp birthday;
    private String email;
    private String phone;
    private Boolean is_whatts;
    private Boolean is_active;
    private String typeRegister;
    private Timestamp registerUpdate;

    public RegisterViewDTO(Long id, String name, String cpfCnpj, Timestamp birthday,
                           String email, String phone, Boolean is_whatts, Boolean is_active,
                           TypeRegister typeRegister, Timestamp registerUpdate) {
        this.id = id;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.is_whatts = is_whatts;
        this.is_active = is_active;
        this.typeRegister = typeRegister.toString();
        this.registerUpdate = registerUpdate;
    }
}
