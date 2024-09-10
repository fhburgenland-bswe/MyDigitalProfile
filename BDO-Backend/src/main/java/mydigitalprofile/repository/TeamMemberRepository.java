package mydigitalprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mydigitalprofile.model.TeamMember;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> findByEmail(String email);
}