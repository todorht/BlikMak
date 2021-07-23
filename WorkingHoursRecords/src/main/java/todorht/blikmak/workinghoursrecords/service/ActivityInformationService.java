package todorht.blikmak.workinghoursrecords.service;

import todorht.blikmak.workinghoursrecords.dto.ActivityInformationDto;


import java.util.List;

public interface ActivityInformationService {

    List<ActivityInformationDto> findAll();
    List<ActivityInformationDto> findAllByCardNumber(String cardNumber);
    void save(ActivityInformationDto activityInformationDto);
    List<ActivityInformationDto> getActivitiesForSelectedDate(int day, int month);

}
