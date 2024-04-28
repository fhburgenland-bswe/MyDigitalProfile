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

import mydigitalprofile.model.dto.TeamDto;

@RestController
@RequestMapping(path = "/api/")
public class TeamController {

    @PostMapping(path = "admin/team/createNew")
    public ResponseEntity<String> createNewTeam(@RequestBody TeamDto teamDto) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "user/team/all")
    public ResponseEntity<String> getAllTeams() {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "user/team/{id}")
    public ResponseEntity<String> getTeamById(@PathVariable long id) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "admin/team/update/{id}")
    public ResponseEntity<String> updateTeamById(@PathVariable long id, @RequestBody TeamDto teamDto) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "admin/team/delete/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable long id) {
        // TODO:
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }
}
