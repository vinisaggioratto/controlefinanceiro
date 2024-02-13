package com.vs.cf.entity;

import com.vs.cf.enums.TypeRegister;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String name;
    @Column(length = 18)
    private String cpfCnpj;
    private Timestamp birthday;
    @Column(length = 150)
    private String email;
    @Column(length = 20)
    private String phone;
    private Boolean is_whatts;
    private Boolean is_active;
    private TypeRegister typeRegister;
    private Timestamp registerUpdate;

    public Register(Long id){
        this.id = id;
    }
}
