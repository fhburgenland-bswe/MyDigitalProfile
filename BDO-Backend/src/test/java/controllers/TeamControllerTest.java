package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.TeamController;
import mydigitalprofile.model.dto.TeamDto;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class TeamControllerTest {

    @Autowired
    private TeamController controller;

    @Test
    public void createNewTeamTest() {
        Set<Long> mitarbeiterIds = new HashSet<Long>();
        mitarbeiterIds.add(1L);
        mitarbeiterIds.add(2L);
        mitarbeiterIds.add(3L);
        TeamDto teamDto = new TeamDto("Team-1", "project-1", mitarbeiterIds);

        ResponseEntity<String> response = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }


    @Test
    public void getAllTeamsTest() {
        ResponseEntity<String> response = controller.getAllTeams();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getTeamByIdTest() {
        ResponseEntity<String> response = controller.getTeamById(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void deleteTeamByIdTest() {
        ResponseEntity<String> response = controller.deleteTeamById(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }
}
