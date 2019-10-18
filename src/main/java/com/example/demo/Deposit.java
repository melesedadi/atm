package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 4)
    private String name;

    @NotNull
    private int depositAmount ;

    @NotNull
    private int depositBalance ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountID")
    private Registration registration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(int depositBalance) {
        this.depositBalance = depositBalance;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
