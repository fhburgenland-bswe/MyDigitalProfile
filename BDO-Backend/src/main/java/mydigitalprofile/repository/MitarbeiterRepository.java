package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Mitarbeiter;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {

}
