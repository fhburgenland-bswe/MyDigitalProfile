package databasemodels;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.controller.MitarbeiterController;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.dto.MitarbeiterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MyDigitalProfileApplication.class, properties = "spring.datasource.url=jdbc:h2:mem:DatabaseDtoTest")
@ActiveProfiles("mem")
public class DatabaseDtoTest {


    @Autowired
    private MitarbeiterController controller;

    private void init() {
        MitarbeiterDto mitarbeiterDto = new MitarbeiterDto("PNR-221", "Pia", "MÃ¼ller", "pia1", "password",
                "11.11.1991", "TestStr", "1/1", "1010", "Wien", "Wien", Rolle.ROLE_USER, CareerLevel.SENIOR_MANAGER);
        ResponseEntity<Long> response = controller.createMitarbeiter(mitarbeiterDto);


        List<String> skills = List.of("Java", "Spring", "Hibernate");

        if (response.getStatusCode() == HttpStatus.OK) {
            controller.updateUserSkill(mitarbeiterDto.getUsername(), skills);

        }
    }

    @Test
    public void updateUserSkillTest() {
        init();

        MitarbeiterDto mitarbeiter = controller.getUser("pia1").getBody();
        assertTrue(mitarbeiter != null);
        assertTrue(mitarbeiter.getSkills().size() > 0);
    }
}
