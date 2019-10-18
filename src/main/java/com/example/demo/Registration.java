package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountID;
    @NotNull
    @Size(min = 4)
    private String name;
    @NotNull
    private String address ;

    @NotNull
    @Size(min = 9, max = 9)
    private String phone;

    @NotNull
    @Size(min = 5, max = 100)
    private String description;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Withdraw> withdraws;
    public Set<Deposit> deposits;

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(Set<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }
}
