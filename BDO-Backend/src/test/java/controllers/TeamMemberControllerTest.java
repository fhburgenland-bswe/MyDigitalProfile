package controllers;

import mydigitalprofile.controller.TeamMemberController;
import mydigitalprofile.model.TeamMember;
import mydigitalprofile.service.TeamMemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamMemberControllerTest {

    @Mock
    private TeamMemberService service;

    @InjectMocks
    private TeamMemberController controller;

    @Mock
    private Principal principal;

    public TeamMemberControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTeamMemberById() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(teamMember));

        ResponseEntity<TeamMember> response = controller.getTeamMemberById(1L);

        assertEquals(ResponseEntity.ok(teamMember), response);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testCreateTeamMember() {
        TeamMember teamMember = new TeamMember();
        when(service.save(any(TeamMember.class))).thenReturn(teamMember);

        TeamMember createdTeamMember = controller.createTeamMember(teamMember);

        assertNotNull(createdTeamMember);
        assertEquals(teamMember, createdTeamMember);
    }

    @Test
    void testUpdateTeamMember() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(teamMember));
        when(service.save(any(TeamMember.class))).thenReturn(teamMember);
        when(principal.getName()).thenReturn("supervisor@example.com");
        when(service.findByEmail("supervisor@example.com")).thenReturn(Optional.of(teamMember));

        ResponseEntity<TeamMember> response = controller.updateTeamMember(1L, teamMember, principal);

        assertEquals(ResponseEntity.ok(teamMember), response);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testDeleteTeamMember() {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(teamMember));
        when(principal.getName()).thenReturn("supervisor@example.com");
        when(service.findByEmail("supervisor@example.com")).thenReturn(Optional.of(teamMember));

        ResponseEntity<Void> response = controller.deleteTeamMember(1L, principal);

        assertEquals(ResponseEntity.noContent().build(), response);
    }
}