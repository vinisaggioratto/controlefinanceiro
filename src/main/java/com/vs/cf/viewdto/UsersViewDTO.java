package com.vs.cf.viewdto;

import com.vs.cf.entity.Register;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UsersViewDTO {

    private Long id;
    private String register;
    private String username;
    private Timestamp userUpdate;

    public UsersViewDTO(Long id, Register register, String username, Timestamp userUpdate) {
        this.id = id;
        this.register = register.getName();
        this.username = username;
        this.userUpdate = userUpdate;
    }
}
