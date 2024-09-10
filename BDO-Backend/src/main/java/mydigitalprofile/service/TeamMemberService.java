package mydigitalprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mydigitalprofile.model.TeamMember;
import mydigitalprofile.repository.TeamMemberRepository;

@Service
public class TeamMemberService {
    @Autowired
    private TeamMemberRepository repository;

    public List<TeamMember> findAll() {
        return repository.findAll();
    }

    public Optional<TeamMember> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<TeamMember> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public TeamMember save(TeamMember teamMember) {
        return repository.save(teamMember);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}