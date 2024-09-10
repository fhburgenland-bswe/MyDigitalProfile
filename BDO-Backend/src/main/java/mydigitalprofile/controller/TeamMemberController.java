package mydigitalprofile.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import mydigitalprofile.model.TeamMember;
import mydigitalprofile.service.TeamMemberService;

@RestController
@RequestMapping("/api/team-members")
@Validated
public class TeamMemberController {
    @Autowired
    private TeamMemberService service;

    @GetMapping
    public List<TeamMember> getAllTeamMembers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMember> getTeamMemberById(@PathVariable Long id) {
        Optional<TeamMember> teamMember = service.findById(id);
        return teamMember.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TeamMember createTeamMember(@Valid @RequestBody TeamMember teamMember) {
        return service.save(teamMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMember> updateTeamMember(@PathVariable Long id, @Valid @RequestBody TeamMember teamMemberDetails, Principal principal) {
        if (hasEditRights(id, principal)) {
            Optional<TeamMember> teamMember = service.findById(id);
            if (teamMember.isPresent()) {
                TeamMember updatedTeamMember = teamMember.get();
                updatedTeamMember.setName(teamMemberDetails.getName());
                updatedTeamMember.setRole(teamMemberDetails.getRole());
                updatedTeamMember.setEmail(teamMemberDetails.getEmail());
                return ResponseEntity.ok(service.save(updatedTeamMember));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).build(); // Forbidden
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Long id, Principal principal) {
        if (hasEditRights(id, principal)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build(); // Forbidden
        }
    }

    private boolean hasEditRights(Long teamMemberId, Principal principal) {
        String currentUserEmail = principal.getName();
        Optional<TeamMember> currentUser = service.findByEmail(currentUserEmail);
        if (currentUser.isPresent()) {
            TeamMember currentTeamMember = currentUser.get();
            Optional<TeamMember> teamMember = service.findById(teamMemberId);
            if (teamMember.isPresent()) {
                TeamMember member = teamMember.get();
                return member.getId().equals(currentTeamMember.getId()) ||
                        (member.getSupervisor() != null && member.getSupervisor().getId().equals(currentTeamMember.getId()));
            }
        }
        return false;
    }
}