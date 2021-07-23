package todorht.blikmak.workinghoursrecords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todorht.blikmak.workinghoursrecords.dto.ActivityInformationDto;
import todorht.blikmak.workinghoursrecords.service.ActivityInformationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityInformationRestController {

    private final ActivityInformationService activityInformationService;

    public ActivityInformationRestController(ActivityInformationService activityInformationService) {
        this.activityInformationService = activityInformationService;
    }

    @GetMapping()
    public ResponseEntity<List<ActivityInformationDto>> getActivities(){
            List<ActivityInformationDto> activityInformationList = activityInformationService.findAll();
            if(activityInformationList == null){
                return ResponseEntity.notFound().build();
            }else return ResponseEntity.ok().body(activityInformationList);
    }

    @PostMapping("/insert")
    public void makeCorrection(@RequestBody ActivityInformationDto activityInformationDto){
        activityInformationService.save(activityInformationDto);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<List<ActivityInformationDto>> getActivitiesByEmployee(@PathVariable(name = "cardNumber") String cardNum) {
        List<ActivityInformationDto> activities = this.activityInformationService.findAllByCardNumber(cardNum);
        if (activities.isEmpty()) {
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(activities);
    }

    @GetMapping("/daily")
    private ResponseEntity<List<ActivityInformationDto>> getActivitiesDaily(@RequestParam(name= "day", required = false) Integer day,
                                                                                        @RequestParam(name = "month", required = false) Integer month){
        List<ActivityInformationDto> result = this.activityInformationService.getActivitiesForSelectedDate(day,month);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }
}
