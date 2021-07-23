package todorht.blikmak.workinghoursrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todorht.blikmak.workinghoursrecords.models.ActivityInformation;

@Repository
public interface ActivityInformationRepository extends JpaRepository<ActivityInformation, Long> {
}
