package com.vs.cf.entity;

import com.vs.cf.enums.StatusPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "inputs")
public class Inputs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    private BigDecimal value;
    private Timestamp date;
    private StatusPayment statusPayment;
    @Column(length = 255, nullable = true)
    private String notes;

    public Inputs(Long id){
        this.id = id;
    }
}
