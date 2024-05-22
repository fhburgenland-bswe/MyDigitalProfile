package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.MitarbeiterController;
import mydigitalprofile.controller.TeamController;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.Team;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.TeamDto;
import mydigitalprofile.repository.MitarbeiterRepository;
import mydigitalprofile.repository.TeamRepository;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class TeamControllerTest {

    @Autowired
    private TeamController controller;
    @Autowired
    private MitarbeiterController controllerM;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    private Set<Long> setUp(String username1, String username2, String pnr1, String pnr2) {
        Set<Long> ids = new HashSet<Long>();
        MitarbeiterDto dto1 = new MitarbeiterDto(pnr1, "Jack", "Mustermann", username1, "password", "11.11.2011",
                "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.SENIOR_CONSULTANT);
        MitarbeiterDto dto2 = new MitarbeiterDto(pnr2, "Jack", "Mustermann", username2, "password1", "11.11.2001",
                "TestStr", "1/2", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.JUNIOR_CONSULTANT);
        ResponseEntity<Long> response1 = controllerM.createMitarbeiter(dto1);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(200, response1.getStatusCodeValue());
        ResponseEntity<Long> response2 = controllerM.createMitarbeiter(dto2);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(200, response2.getStatusCodeValue());
        ids.add(response1.getBody());
        ids.add(response2.getBody());
        return ids;
    }

    @Test
    public void createNewTeamAndFindTeamTest() {
        Set<Long> mitarbeiterIds = setUp("jack10", "jack11", "PNR-10", "PNR-11");
        TeamDto teamDto = new TeamDto("Team-11", mitarbeiterIds);

        ResponseEntity<Long> response = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        Optional<Team> team = teamRepository.findById(response.getBody());
        assertTrue(team.isPresent());
        ResponseEntity<TeamDto> getResponse = controller.getTeamById(response.getBody());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(team.get().getTeamName(), getResponse.getBody().getTeamName());
    }

    @Test
    public void getAllTeamsTest() {
        Set<Long> mitarbeiterIds = setUp("jack12", "jack13", "PNR-12", "PNR-13");
        TeamDto teamDto = new TeamDto("Team-2", mitarbeiterIds);
        ResponseEntity<Long> saveResponse = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());
        ResponseEntity<List<TeamDto>> response = controller.getAllTeams();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void addAndRemoveMitabeiterTest() {
        Set<Long> mitarbeiterIds = setUp("jack14", "jack15", "PNR-14", "PNR-15");
        TeamDto teamDto = new TeamDto("Team-3", mitarbeiterIds);
        ResponseEntity<Long> saveResponse = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());

        MitarbeiterDto dto = new MitarbeiterDto("PNR-16", "Jack", "Mustermann", "jack16", "password333", "11.11.2001",
                "TestStr", "1/2", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.JUNIOR_CONSULTANT);
        ResponseEntity<Long> response1 = controllerM.createMitarbeiter(dto);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        List<Long> ids = new ArrayList<Long>();
        ids.add(response1.getBody());
        ResponseEntity<String> addResponse = controller.addMitarbeiter(saveResponse.getBody(), ids);
        assertEquals(HttpStatus.OK, addResponse.getStatusCode());
        assertTrue(teamRepository.findById(saveResponse.getBody()).isPresent());
        Team team = teamRepository.findById(saveResponse.getBody()).get();
        assertEquals(3, team.getMitarbeiters().size());

        ResponseEntity<String> removeResponse = controller.removeMitarbeiter(saveResponse.getBody(), ids);
        assertEquals(HttpStatus.OK, removeResponse.getStatusCode());
        Team updatedTeam = teamRepository.findById(saveResponse.getBody()).get();
        assertEquals(2, updatedTeam.getMitarbeiters().size());
    }

    @Test
    public void findTeamMembersByUsernameTest() {
        Set<Long> mitarbeiterIds = setUp("anna12", "alice22", "PNR-321", "PNR-443");
        controllerM.updateUserSkill("anna12", List.of("JAVA", ".NET"));
        controllerM.updateUserSkill("alice22", List.of("JS", "PYTHON"));

        MitarbeiterDto dto = new MitarbeiterDto("PNR-72", "Jerry", "Mustermann", "jerry3321", "password333", "11.11.2001",
                "TestStr", "1/2", "1010", "Wien", "Wien", Rolle.ROLE_TEAMLEADER, CareerLevel.MANAGER);
        ResponseEntity<Long> response1 = controllerM.createMitarbeiter(dto);
        assertEquals(HttpStatus.OK, response1.getStatusCode());

        TeamDto teamDto = new TeamDto("Team-007", mitarbeiterIds);
        ResponseEntity<Long> saveResponse = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());

        ResponseEntity<String> addResponse = controller.addMitarbeiter(saveResponse.getBody(), List.of(response1.getBody()));
        assertEquals(HttpStatus.OK, addResponse.getStatusCode());
        assertTrue(teamRepository.findById(saveResponse.getBody()).isPresent());


        ResponseEntity<List<MitarbeiterDto>> teamMembersResponse = controller.getTeamMembers(dto.getUsername());
        assertEquals(HttpStatus.OK, teamMembersResponse.getStatusCode());
        assertTrue(!teamMembersResponse.getBody().isEmpty());
        assertTrue(teamMembersResponse.getBody().size() == 3);


    }

    @Test
    public void deleteTeamByIdTest() {
        Set<Long> mitarbeiterIds = setUp("jack17", "jack18", "PNR-17", "PNR-18");
        TeamDto teamDto = new TeamDto("Team-4", mitarbeiterIds);
        ResponseEntity<Long> saveResponse = controller.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());
        ResponseEntity<String> response = controller.deleteTeamById(saveResponse.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(teamRepository.findById(saveResponse.getBody()).isEmpty());
        Mitarbeiter mitarbeiter1 = mitarbeiterRepository.findByUsername("jack17");
        Mitarbeiter mitarbeiter2 = mitarbeiterRepository.findByUsername("jack18");
        assertTrue(mitarbeiter1.getTeam() == null);
        assertTrue(mitarbeiter2.getTeam() == null);
    }
}