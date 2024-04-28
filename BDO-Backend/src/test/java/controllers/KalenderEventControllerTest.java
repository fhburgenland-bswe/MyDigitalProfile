package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.KalenderEventController;
import mydigitalprofile.model.dto.KalenderEventDto;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class KalenderEventControllerTest {


    @Autowired
    private KalenderEventController controller;

    @Test
    public void addKalenderEventTest() {
        KalenderEventDto eventDto = new KalenderEventDto("01.10.2022", "12.10.2022", "Urlaub", "blah blah");
        ResponseEntity<String> response =  controller.addKalenderEvent(0, eventDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void getAllKalenderEventsByUserIdTest() {
        ResponseEntity<String> response =  controller.getAllKalenderEventsByUserId(0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void updateKalenderEventTest() {
        KalenderEventDto eventDto = new KalenderEventDto("01.10.2022", "12.11.2022", "Urlaub", "blah blah");
        ResponseEntity<String> response =  controller.updateKalenderEvent(0, 0, eventDto);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }

    @Test
    public void deleteKalenderEventTest() {
        ResponseEntity<String> response =  controller.deleteKalenderEvent(0, 0);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertEquals(501, response.getStatusCodeValue());
    }
}
