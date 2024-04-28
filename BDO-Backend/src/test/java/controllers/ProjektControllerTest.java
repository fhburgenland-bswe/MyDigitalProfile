package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.ProjektController;
import mydigitalprofile.model.dto.ProjektDto;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class ProjektControllerTest {

    @Autowired
    private ProjektController controller;

    @Test
    public void createProjektTest() {
        ProjektDto projektDto = new ProjektDto("project-1", "test", "01.01.2001", "10.10.2010", "test-desc");
        ResponseEntity<String> response = controller.createProject(projektDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getProjectByIdTest() {
        ResponseEntity<String> response = controller.getProjectById(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getAllProjectsTest() {
        ResponseEntity<String> response = controller.getAllProjects();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void updateProjectById() {
        ProjektDto projektDto = new ProjektDto("project-1", "test", "01.01.2001", "12.10.2010", "test-desc");
        ResponseEntity<String> response = controller.updateProjectById(0, projektDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void deleteProjectTest() {
        ResponseEntity<String> response = controller.deleteProject(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }
}
