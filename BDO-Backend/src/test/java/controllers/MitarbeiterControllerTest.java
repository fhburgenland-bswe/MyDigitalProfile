package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.MitarbeiterController;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.model.dto.loginDto;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class MitarbeiterControllerTest {

    @Autowired
    private MitarbeiterController controller;

    @Test
    public void registerTest() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-1", "Jack", "Mustermann", "jack1", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien");

        ResponseEntity<String> response = controller.createMitarbeiter(mitarbeiterDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());

    }

    @Test
    public void loginTest() {
        loginDto dto = new loginDto("username", "password");
        ResponseEntity<String> response = controller.login(dto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getUserTest() {
        ResponseEntity<String> response = controller.getUser("username", 0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getAllUsersTest() {
        ResponseEntity<String> response = controller.getUsers();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void updateUserTest() {
        MitarbeiterDto dto = new MitarbeiterDto("PNR-1", "Jack", "Mustermann", "jack1", "password",
                "11.11.2011", "TestStr", "1/1", "1010", "Wien");

        ResponseEntity<String> response = controller.updateUser("jack1", dto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void updateUserAsAdminTest() {
        MitarbeiterRolleDto rolleDto = new MitarbeiterRolleDto("username", Rolle.TeamLeiter);
        ResponseEntity<String> response = controller.updateUserAsAdmin(rolleDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void addSkillsTest() {
        List<String> skillsToAdd = new ArrayList<String>();
        skillsToAdd.add("Java");
        skillsToAdd.add("JavaScript");

        ResponseEntity<String> response = controller.updateUserSkill(skillsToAdd);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }


    @Test
    public void deleteUserTest() {
        ResponseEntity<String> response = controller.deleteUser(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }
}
