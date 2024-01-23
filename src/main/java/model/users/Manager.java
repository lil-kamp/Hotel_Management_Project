package model.users;

import javax.persistence.Entity;

@Entity
public class Manager extends Person{

    private String personCode;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Manager(){}

    public Manager(Long id, String firstName, String lastName, String email, String password, String personCode) {
        super(id, firstName, lastName, email, password);
        this.personCode = personCode;
    }
}
