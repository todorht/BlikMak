package todorht.blikmak.workinghoursrecords.service.impl;

import org.springframework.stereotype.Service;
import todorht.blikmak.workinghoursrecords.dto.ActivityInformationDto;
import todorht.blikmak.workinghoursrecords.models.ActivityInformation;
import todorht.blikmak.workinghoursrecords.models.EmployeeCard;
import todorht.blikmak.workinghoursrecords.repository.ActivityInformationRepository;
import todorht.blikmak.workinghoursrecords.repository.EmployeeCardRepository;
import todorht.blikmak.workinghoursrecords.service.ActivityInformationService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityInformationServiceImpl implements ActivityInformationService {

    private final ActivityInformationRepository activityRepository;
    private final EmployeeCardRepository employeeCardRepository;

    public ActivityInformationServiceImpl(ActivityInformationRepository activityRepository, EmployeeCardRepository employeeCardRepository) {
        this.activityRepository = activityRepository;
        this.employeeCardRepository = employeeCardRepository;
    }


    @Override
    public List<ActivityInformationDto> findAll() {
        return this.activityRepository.findAll().stream()
                .map(activityInformation -> new ActivityInformationDto(activityInformation.getDateTime(),
                        activityInformation.getEmployeeCard().getName(),
                        activityInformation.getActivityType()))
                .sorted(Comparator.comparing(ActivityInformationDto::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityInformationDto> findAllByCardNumber(String cardNumber) {
        return this.activityRepository.findAll().parallelStream()
                .filter(activity -> activity.getEmployeeCard().getID().equals(cardNumber))
                .map(activityInformation -> new ActivityInformationDto(activityInformation.getDateTime(),
                        activityInformation.getEmployeeCard().getName(),
                        activityInformation.getActivityType()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ActivityInformationDto activityInformationDto) {
        EmployeeCard employeeCard = this.employeeCardRepository.findById(activityInformationDto.getEmployeeId()).orElseThrow();
        this.activityRepository.save(new ActivityInformation(activityInformationDto.getDateTime(),
                employeeCard,
                activityInformationDto.getActivityType().ordinal()+1));
    }

    @Override
    public List<ActivityInformationDto> getActivitiesForSelectedDate(int day, int month) {
        return this.activityRepository.findAll().stream().parallel()
                .filter(activityInformation -> activityInformation.getDateTime().getMonthValue()==month
                        && activityInformation.getDateTime().getDayOfMonth()==day)
                .map(activityInformation -> new ActivityInformationDto(activityInformation.getDateTime(),
                activityInformation.getEmployeeCard().getName(),
                activityInformation.getActivityType()))
                .collect(Collectors.toList());
    }


}
