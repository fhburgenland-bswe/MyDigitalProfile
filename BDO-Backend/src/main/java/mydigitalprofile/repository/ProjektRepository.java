package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Projekt;

@Repository
public interface ProjektRepository extends JpaRepository<Projekt, Long> {

}
