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
}
