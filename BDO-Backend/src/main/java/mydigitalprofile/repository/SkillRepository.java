package mydigitalprofile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(value = "SELECT s.skillName FROM Skill s "
            + "WHERE s.mitarbeiter.username = :username")
    public List<String> findSkillsByUsername(@Param(value = "username") String username);

}
