package com.vs.cf.entity;

import com.vs.cf.enums.FormOfPayment;
import com.vs.cf.enums.StatusPayment;
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
@Table(name = "outputs")
public class Outputs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;
    @Column(length = 255)
    private String description;
    private BigDecimal value;
    private BigDecimal finalValue;
    private FormOfPayment formOfPayment;
    private Integer installments;
    private Integer daysBetweenInstallments;
    private Date purchaseDate;
    @Column(length = 255, nullable = true)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    private StatusPayment statusPayment;

    public Outputs(Long id){
        this.id = id;
    }
}
