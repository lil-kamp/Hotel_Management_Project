package model.users;

import model.Hotel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Worker extends Person{

    private String personCode;
    private Double salary;
    private Double balance;
    @ManyToOne
    private Hotel hotel;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
