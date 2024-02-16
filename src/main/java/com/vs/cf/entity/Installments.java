package com.vs.cf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "installments")
public class Installments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "outputs_id")
    private Outputs outputs;
    private Integer installmentNumber;
    private BigDecimal value;
    private Date installmentDue;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Installments(Long id){
        this.id = id;
    }
}
