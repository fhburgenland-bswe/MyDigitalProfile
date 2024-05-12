package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.MitarbeiterController;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.Skill;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.repository.MitarbeiterRepository;
import mydigitalprofile.repository.SkillRepository;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class MitarbeiterControllerTest {

    @Autowired
    private MitarbeiterController controller;

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    private SkillRepository skillRepository;


    @Test
    public void registerAndGetUserTest() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-1", "Jack", "Mustermann", "jack1", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.SENIOR_MANAGER);

        ResponseEntity<Long> response = controller.createMitarbeiter(mitarbeiterDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        ResponseEntity<MitarbeiterDto> response2 = controller.getUser(mitarbeiterDto.getUsername(), response.getBody().longValue());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        MitarbeiterDto mitarbeiterDto2 = response2.getBody();
        assertEquals(mitarbeiterDto.getPnr(), mitarbeiterDto2.getPnr());

    }



    @Test
    public void getAllUsersTest() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-2", "Jack", "Mustermann", "jack2", "password",
                "11.01.2011", "TestStr", "2/1", "1040", "Wien", "Wien", Rolle.ROLE_ADMIN, CareerLevel.DIRECTOR);
        controller.createMitarbeiter(mitarbeiterDto);

        ResponseEntity<List<MitarbeiterDto>> response = controller.getUsers();
        List<MitarbeiterDto> list = response.getBody();
        assertTrue(list.size() > 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    public void updateUserTest() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-1", "Jack", "Mustermann", "jack3", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.MANAGER);

        ResponseEntity<Long> response = controller.createMitarbeiter(mitarbeiterDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        MitarbeiterDto dto = new MitarbeiterDto("PNR-1", "Jack", "Mustermann", "jack3", "password",
                "11.11.2011", "newTestStrasse", "1/1", "1050", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.MANAGER);

        ResponseEntity<String> response2 = controller.updateUser("jack3", dto);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(200, response2.getStatusCodeValue());
        assertEquals("User: jack3 updated!", response2.getBody());

        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername("jack3");
        assertTrue(mitarbeiter != null);
        assertEquals(dto.getStrasse(), mitarbeiter.getAddress().getStrasse());
        assertEquals(dto.getPlz(), mitarbeiter.getAddress().getPlz());
    }

    @Test
    public void updateNonExistedUserAsAdminTest() {
        MitarbeiterRolleDto rolleDto = new MitarbeiterRolleDto("username12", Rolle.ROLE_USER);
        assertThrows(ResponseStatusException.class, ()-> controller.updateUserRole(rolleDto), "User is not available!");
    }


    @Test
    public void updateUserAsAdminTest() {

        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-5", "Jack", "Mustermann", "jack4", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.JUNIOR_CONSULTANT);

        ResponseEntity<Long> response = controller.createMitarbeiter(mitarbeiterDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        MitarbeiterRolleDto rolleDto = new MitarbeiterRolleDto("jack4", Rolle.ROLE_USER);

        ResponseEntity<String> response2 = controller.updateUserRole(rolleDto);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(200, response2.getStatusCodeValue());

        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername("jack4");
        assertTrue(mitarbeiter != null);
        assertEquals(Rolle.ROLE_USER, mitarbeiter.getRolle());

    }

    @Test
    public void addSkillsAndDeleteUserTest() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-5", "Jack", "Mustermann", "jack5", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.JUNIOR_CONSULTANT);

        ResponseEntity<Long> response = controller.createMitarbeiter(mitarbeiterDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<String> skillsToAdd = new ArrayList<String>();
        skillsToAdd.add("Java");
        skillsToAdd.add("JavaScript");

        ResponseEntity<String> response2 = controller.updateUserSkill("jack5", skillsToAdd);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(200, response2.getStatusCodeValue());

        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername("jack5");
        assertTrue(mitarbeiter != null);
        assertEquals(2, mitarbeiter.getSkills().size());


        ResponseEntity<String> deleteResponse = controller.deleteUser(mitarbeiter.getMaId());
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        assertEquals(200, deleteResponse.getStatusCodeValue());

        mitarbeiter = mitarbeiterRepository.findByUsername("jack5");
        assertNull(mitarbeiter);
        List<Skill> skills = skillRepository.findAll();
        assertTrue(skills.isEmpty());

    }
    @AfterEach
    public void cleanUp() {
        mitarbeiterRepository.deleteAll();
        skillRepository.deleteAll();
    }



}
