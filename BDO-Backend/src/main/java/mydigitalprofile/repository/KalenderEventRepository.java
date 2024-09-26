package mydigitalprofile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.KalenderEvent;

@Repository
public interface KalenderEventRepository extends JpaRepository<KalenderEvent, Long> {

    // Abfrage, um Kalendereinträge für einen Benutzer anhand seiner ID zu finden
    @Query(value = "SELECT k FROM KalenderEvent k JOIN k.mitarbeiter m WHERE m.maId =:id")
    public List<KalenderEvent> findKalenderEventsForUserById(@Param(value = "id") long id);
}
