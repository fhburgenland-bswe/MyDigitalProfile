package mydigitalprofile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mydigitalprofile.model.dto.KalenderEventDto;

@RestController
@RequestMapping(path = "/api/")
public class KalenderEventController {

    @PostMapping(path = "user/kalenderEvent/{id}")
    public ResponseEntity<String> addKalenderEvent(@PathVariable long id, @RequestBody KalenderEventDto eventDto) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "user/kalenderEvent/all/{id}")
    public ResponseEntity<String> getAllKalenderEventsByUserId(@PathVariable long id) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "user/kalenderEvent/{id}/{kalenderEventId}")
    public ResponseEntity<String> updateKalenderEvent(@PathVariable long id, @PathVariable long kalenderEventId,
                                                      @RequestBody KalenderEventDto eventDto) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "user/kalenderEvent/{id}/delete/{kalenderEventId}")
    public ResponseEntity<String> deleteKalenderEvent(@PathVariable long id, @PathVariable long kalenderEventId) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }
}
