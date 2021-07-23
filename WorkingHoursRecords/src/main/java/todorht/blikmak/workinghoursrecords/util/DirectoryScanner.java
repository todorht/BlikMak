package todorht.blikmak.workinghoursrecords.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import todorht.blikmak.workinghoursrecords.models.ActivityInformation;
import todorht.blikmak.workinghoursrecords.repository.ActivityInformationRepository;
import todorht.blikmak.workinghoursrecords.repository.EmployeeCardRepository;
import todorht.blikmak.workinghoursrecords.service.EmployeeCardService;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@Component
public class DirectoryScanner {

    private String lastFile;
    private static Path dir = Paths.get("C:\\Users\\neznam\\Desktop\\test");

    private final ActivityInformationRepository activityInformationRepository;
    private final EmployeeCardService employeeCardService;

    public DirectoryScanner(ActivityInformationRepository activityInformationRepository, EmployeeCardService employeeCardService) {
        this.activityInformationRepository = activityInformationRepository;
        this.employeeCardService = employeeCardService;
        this.lastFile="";
    }

    @Async
    @Scheduled(fixedDelay = 20000)
    public void scan() throws IOException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        WatchKey key;
        try {
            key = watcher.take();
        } catch (InterruptedException ex) {
            System.out.println("There is no new files.");
            return;
        }

        List<String> files = new ArrayList<>();
        for (WatchEvent<?> event : key.pollEvents()) {
            files.add(event.context().toString());
        }
        System.out.println("Reading...");

        for (String fileName : files) {

            try {
                Scanner sc = new Scanner(new File(dir.toAbsolutePath() + "\\" + fileName));
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String[] parts = sc.nextLine().split(" ");
                    String[] date = parts[5].split("/");
                    String[] time = parts[6].split(":");
                    LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
                    this.activityInformationRepository.saveAndFlush(new ActivityInformation(localDateTime,employeeCardService.findByCardNumber(parts[9]).get(),Integer.parseInt(String.valueOf(parts[20].charAt(0)))));
                }
                this.lastFile = fileName;
            } catch (FileNotFoundException ex) {
                System.out.println("File not found.");
            }
            System.out.println("Finish.");

        }


    }

}

