package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import mydigitalprofile.controller.ProjektController;
import mydigitalprofile.controller.TeamController;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.Projekt;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.Team;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.ProjektDto;
import mydigitalprofile.model.dto.TeamDto;
import mydigitalprofile.repository.ProjektRepository;
import mydigitalprofile.repository.TeamRepository;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class ProjektControllerTest {

    @Autowired
    private ProjektController controller;

    @Autowired
    private MitarbeiterController controllerM;

    @Autowired
    private TeamController controllerT;

    @Autowired
    private ProjektRepository projektRepository;

    @Autowired
    private TeamRepository teamRepository;

    public long setUp(String username1, String username2, String pnr1, String pnr2, String teamName) {
        Set<String> usernames = new HashSet<>();
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
        usernames.add(username1);
        usernames.add(username2);

        TeamDto teamDto = new TeamDto(teamName, usernames);
        ResponseEntity<Long> response = controllerT.createNewTeam(teamDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        return response.getBody();
    }

    @Test
    public void createAndGetProjektTest() {
        ProjektDto projektDto = new ProjektDto("project-2", "test", "01.01.2001", "10.10.2010", "test-desc");
        ResponseEntity<Long> response = controller.createProject(projektDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        Projekt projekt = projektRepository.findByProjektName("project-2");
        assertTrue(projekt != null);

        ResponseEntity<ProjektDto> getResponse = controller.getProjectById(response.getBody());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(200, getResponse.getStatusCodeValue());
        assertEquals(projektDto.getVolumina(), getResponse.getBody().getVolumina());
        assertEquals(projektDto.getProjektName(), getResponse.getBody().getProjektName());

    }

    @Test
    public void getAllProjectsTest() {
        ProjektDto projektDto = new ProjektDto("project-3", "test", "01.01.2001", "10.10.2010", "test-desc");
        ResponseEntity<Long> response = controller.createProject(projektDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        ResponseEntity<List<ProjektDto>> getResponse = controller.getAllProjects();
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(200, getResponse.getStatusCodeValue());
        assertTrue(getResponse.getBody().size() > 0);
    }

    @Test
    public void updateProjectById() {
        ProjektDto projektDto = new ProjektDto("project-4", "test", "01.05.2005", "10.10.2010", "test-desc");
        ResponseEntity<Long> response = controller.createProject(projektDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        ProjektDto dto = new ProjektDto("new volumina", "01.01.2001", "12.10.2010");
        ResponseEntity<String> updateResponse = controller.updateProjectById(response.getBody(), dto);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals(200, updateResponse.getStatusCodeValue());

        Optional<Projekt> optional = projektRepository.findById(response.getBody());
        assertTrue(optional.isPresent());
        assertEquals(dto.getVolumina(), optional.get().getVolumina());
    }

    @Test
    public void addTeamToProjectTest() {
        long teamId = setUp("alex123", "mona32", "PNR-123", "PNR-32", "Team-25");
        ProjektDto projektDto = new ProjektDto("project-5", "test", "01.05.2005", "10.10.2010", "test-desc");
        ResponseEntity<Long> saveResponse = controller.createProject(projektDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());
        assertEquals(200, saveResponse.getStatusCodeValue());

        ResponseEntity<String> response = controller.addTeamToProject(teamId, saveResponse.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        Optional<Projekt> projektOptional = projektRepository.findById(saveResponse.getBody());
        assertTrue(projektOptional.isPresent());
        assertTrue(projektOptional.get().getTeam() != null);
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        assertTrue(teamOptional.isPresent());
        assertTrue(teamOptional.get().getProjekt() != null);

    }

    @Test
    public void deleteProjectTest() {
        long teamId = setUp("john123", "lisa32", "PNR-124", "PNR-322", "Team-26");
        ProjektDto projektDto = new ProjektDto("project-6", "test", "01.05.2005", "10.10.2010", "test-desc");
        ResponseEntity<Long> saveResponse = controller.createProject(projektDto);
        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());
        assertEquals(200, saveResponse.getStatusCodeValue());

        ResponseEntity<String> response = controller.addTeamToProject(teamId, saveResponse.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        ResponseEntity<String> deleteResponse = controller.deleteProject(saveResponse.getBody());
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        assertEquals(200, deleteResponse.getStatusCodeValue());

        Optional<Team> teamOptional = teamRepository.findById(teamId);
        assertTrue(teamOptional.isPresent());
        assertTrue(teamOptional.get().getProjekt() == null);
    }
}