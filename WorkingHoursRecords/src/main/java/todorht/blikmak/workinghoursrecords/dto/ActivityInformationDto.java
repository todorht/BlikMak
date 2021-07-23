package todorht.blikmak.workinghoursrecords.dto;

import lombok.Getter;
import todorht.blikmak.workinghoursrecords.models.enums.ActivityType;

import java.time.LocalDateTime;

@Getter
public class ActivityInformationDto {

    private LocalDateTime dateTime;
    private String employeeName;
    private String employeeId;
    private ActivityType activityType;

    public ActivityInformationDto(LocalDateTime date, String employeeName, ActivityType activityType) {
        this.dateTime = date;
        this.employeeName = employeeName;
        this.activityType = activityType;

    }
}
