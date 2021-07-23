package todorht.blikmak.workinghoursrecords.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import todorht.blikmak.workinghoursrecords.models.enums.ActivityType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class ActivityInformation {

    @Id
    private LocalDateTime dateTime;
    @OneToOne
    @JoinColumn(name = "employee_card")
    private EmployeeCard employeeCard;
    private ActivityType activityType;

    public ActivityInformation(LocalDateTime date, EmployeeCard employeeCard, int activityType) {
        this.dateTime = date;
        this.employeeCard = employeeCard;
        this.activityType = getActivityType(activityType);
    }

    private ActivityType getActivityType(int activityType){
        switch (activityType){
            case 2: return ActivityType.ИЗЛЕЗ;
            case 3: return ActivityType.СЛУЖБЕН_ИЗЛЕЗ;
            case 4: return ActivityType.СЛУЖБЕН_ВЛЕЗ;
            default: return ActivityType.ВЛЕЗ;
        }
    }
}