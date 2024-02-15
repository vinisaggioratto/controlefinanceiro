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
    private Boolean is_active;

    public UsersViewDTO(Long id, Register register, String username, Timestamp userUpdate,
                        Boolean is_active) {
        this.id = id;
        this.register = register.getName();
        this.username = username;
        this.userUpdate = userUpdate;
        this.is_active = is_active;
    }
}
