package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
