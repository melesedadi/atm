package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min=3)
    private String name;
    @NotNull
    @Size(min = 2)
    private int withdrawAmount;

    @NotNull
    private int accountBalance;

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

    public int getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(int withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
