package todorht.blikmak.workinghoursrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class WorkinghoursrecordsApplication {



    public static void main(String[] args) {
        SpringApplication.run(WorkinghoursrecordsApplication.class, args);
    }

}
