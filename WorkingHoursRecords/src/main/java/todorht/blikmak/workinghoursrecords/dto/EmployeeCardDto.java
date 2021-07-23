package todorht.blikmak.workinghoursrecords.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
public class EmployeeCardDto {

    private String cardNumber;
    private String name;
    private String surname;

    public EmployeeCardDto(String ID, String name, String surname) {
        this.cardNumber = ID;
        this.name = name;
        this.surname = surname;
    }

}