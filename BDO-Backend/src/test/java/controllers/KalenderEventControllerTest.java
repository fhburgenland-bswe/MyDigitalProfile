package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.KalenderEventController;
import mydigitalprofile.controller.MitarbeiterController;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.KalenderEvent;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.dto.KalenderEventDto;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.repository.KalenderEventRepository;
import mydigitalprofile.repository.MitarbeiterRepository;

@SpringBootTest(classes = MyDigitalProfileApplication.class)
@ActiveProfiles("mem")
public class KalenderEventControllerTest {

    @Autowired
    private KalenderEventController controller;

    @Autowired
    private MitarbeiterController controllerM;

    @Autowired
    private KalenderEventRepository kalenderEventRepository;

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    private long setUp(String username1, String pnr1) {
        MitarbeiterDto dto1 = new MitarbeiterDto(pnr1, "David", "Kalendermann", username1, "passwort", "11.11.2012",
                "Testgasse", "1/5", "1070", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.SENIOR_CONSULTANT);

        ResponseEntity<Long> response1 = controllerM.createMitarbeiter(dto1);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(200, response1.getStatusCodeValue());

        return response1.getBody();
    }

    @Test
    public void addAndGetAllKalenderEventTest() {
        long userId = setUp("david11", "PNR-55");
        KalenderEventDto eventDto = new KalenderEventDto("01.10.2022", "12.10.2022", "Urlaub", "Urlaub-Test");
        ResponseEntity<Long> response = controller.addKalenderEvent(userId, eventDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(response.getBody());
        assertTrue(optional.isPresent());
        Optional<Mitarbeiter> optionalUser = mitarbeiterRepository.findById(userId);
        assertTrue(optionalUser.isPresent());

        Mitarbeiter mitarbeiter = optionalUser.get();
        assertTrue(mitarbeiter.getKalenderEvents().size() > 0);


        ResponseEntity<List<KalenderEventDto>> getResponse = controller.getAllKalenderEventsByUserId(userId);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(200, getResponse.getStatusCodeValue());
        assertTrue(getResponse.getBody().size() > 0);
    }



    @Test
    public void updateKalenderEventTest() {
        long userId = setUp("Pob11", "PNR-45");
        KalenderEventDto eventDto = new KalenderEventDto("01.10.2022", "12.10.2022", "Urlaub", "Urlaub-Test");
        ResponseEntity<Long> response = controller.addKalenderEvent(userId, eventDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        KalenderEventDto updateEvent = new KalenderEventDto("01.10.2022", "12.11.2022", "Sick", "ill");
        ResponseEntity<String> updateResponse = controller.updateKalenderEvent(response.getBody(), updateEvent);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(response.getBody());
        assertTrue(optional.isPresent());
        assertEquals("Sick", optional.get().getTyp());
        assertEquals("ill", optional.get().getBeschreibung());
    }

    @Test
    public void deleteKalenderEventTest() {
        long userId = setUp("Pob12", "PNR-35");
        KalenderEventDto eventDto = new KalenderEventDto("01.10.2022", "12.10.2022", "Urlaub", "Urlaub-Test");
        ResponseEntity<Long> response = controller.addKalenderEvent(userId, eventDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        ResponseEntity<String> deleteResponse = controller.deleteKalenderEvent(response.getBody());
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        assertEquals(200, deleteResponse.getStatusCodeValue());
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(response.getBody());
        assertTrue(optional.isEmpty());
        Optional<Mitarbeiter> optionalUser = mitarbeiterRepository.findById(userId);
        assertTrue(optionalUser.isPresent());
        assertTrue(optionalUser.get().getKalenderEvents().size() == 0);
    }
    @AfterEach
    public void cleanUp() {
        kalenderEventRepository.deleteAll();
        mitarbeiterRepository.deleteAll();
    }
}