package mydigitalprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import mydigitalprofile.service.KalenderEventService;

@RestController
@RequestMapping(path = "/api/")
public class KalenderEventController {
    @Autowired
    private KalenderEventService service;

    @PostMapping(path = "user/kalenderEvent/{id}")
    public ResponseEntity<Long> addKalenderEvent(@PathVariable long id, @RequestBody KalenderEventDto eventDto) {
        Long eventId = service.addKalenderEvent(id, eventDto);
        return new ResponseEntity<>(eventId, HttpStatus.OK);
    }

    @GetMapping(path = "user/kalenderEvent/all/{id}")
    public ResponseEntity<List<KalenderEventDto>> getAllKalenderEventsByUserId(@PathVariable long id) {
        List<KalenderEventDto> kalenderEventDtos = service.getAllEventForUserById(id);
        return new ResponseEntity<>(kalenderEventDtos, HttpStatus.OK);
    }

    @PutMapping(path = "user/kalenderEvent/update/{kalenderEventId}")
    public ResponseEntity<String> updateKalenderEvent(@PathVariable long kalenderEventId,
                                                      @RequestBody KalenderEventDto eventDto) {
        service.updateEvent(kalenderEventId, eventDto);
        return new ResponseEntity<String>("Calender was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping(path = "user/kalenderEvent/delete/{kalenderEventId}")
    public ResponseEntity<String> deleteKalenderEvent(@PathVariable long kalenderEventId) {
        service.deleteEvent(kalenderEventId);
        return new ResponseEntity<String>("Calender was deleted successfully!", HttpStatus.OK);
    }
}