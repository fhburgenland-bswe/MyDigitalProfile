package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
