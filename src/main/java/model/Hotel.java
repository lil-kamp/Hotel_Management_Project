package model;

import model.enums.HotelStatus;
import model.users.Customer;
import model.users.Manager;
import model.users.Worker;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Manager manager;
    @OneToMany(mappedBy = "hotel")
    private Set<Worker> workers;
    @OneToMany(mappedBy = "hotel")
    private Set<Customer> customers;
    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private HotelStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public HotelStatus getStatus() {
        return status;
    }

    public void setStatus(HotelStatus status) {
        this.status = status;
    }
}
