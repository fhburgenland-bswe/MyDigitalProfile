package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.KalenderEvent;

@Repository
public interface KalenderEventRepository extends JpaRepository<KalenderEvent, Long> {

}
