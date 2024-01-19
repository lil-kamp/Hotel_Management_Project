package model.users;

import model.Hotel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends Person{

    private String nationalId;
    @ManyToOne
    private Hotel hotel;

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
