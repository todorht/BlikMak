package todorht.blikmak.workinghoursrecords.models;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Embeddable
@NoArgsConstructor
public class EmployeeCard {

    @Id
    private String cardNumber;

    private String name;
    private String surname;

    public EmployeeCard(String ID, String name, String surname) {
        this.cardNumber = ID;
        this.name = name;
        this.surname = surname;
    }

    public String getID() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
