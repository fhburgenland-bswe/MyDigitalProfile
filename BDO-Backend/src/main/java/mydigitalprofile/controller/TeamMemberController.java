// src/main/java/mydigitalprofile/controller/TeamMemberController.java
package mydigitalprofile.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mydigitalprofile.model.TeamMember;
import mydigitalprofile.service.TeamMemberService;

@RestController
@RequestMapping("/api/team-members")
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
    public TeamMember createTeamMember(@RequestBody TeamMember teamMember) {
        return service.save(teamMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMember> updateTeamMember(@PathVariable Long id, @RequestBody TeamMember teamMemberDetails) {
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}