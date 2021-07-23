package todorht.blikmak.workinghoursrecords.service;

import todorht.blikmak.workinghoursrecords.dto.EmployeeCardDto;
import todorht.blikmak.workinghoursrecords.models.EmployeeCard;

import java.util.List;
import java.util.Optional;

public interface EmployeeCardService {

    List<EmployeeCard> findAll();
    Optional<EmployeeCard> save(EmployeeCardDto employeeCardDto);
    Optional<EmployeeCard> findByCardNumber(String cardNumber);
}
