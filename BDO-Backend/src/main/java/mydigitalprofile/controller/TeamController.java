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
import org.springframework.web.server.ResponseStatusException;

import mydigitalprofile.model.dto.TeamDto;
import mydigitalprofile.service.TeamSerivce;

@RestController
@RequestMapping(path = "/api/")
public class TeamController {

    @Autowired
    private TeamSerivce teamSerivce;

    @PostMapping(path = "admin/team/createNew")
    public ResponseEntity<Long> createNewTeam(@RequestBody TeamDto teamDto) {
        Long teamId = teamSerivce.createNewTeam(teamDto);
        if (teamId == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Somthing went wrong while saving this team!");
        }
        return new ResponseEntity<>(teamId, HttpStatus.OK);
    }

    @GetMapping(path = "user/team/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable long id) {
        TeamDto team = teamSerivce.getTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping(path = "user/team/all")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamSerivce.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PutMapping(path = "admin/team/addMitarbeiter/{id}")
    public ResponseEntity<String> addMitarbeiter(@PathVariable long id, @RequestBody List<Long> mitarbeiterIds) {
        teamSerivce.addMitarbeiter(id, mitarbeiterIds);
        return new ResponseEntity<String>("Mitarbeiter(s) added!", HttpStatus.OK);
    }

    @PutMapping(path = "admin/team/removeMitarbeiter/{id}")
    public ResponseEntity<String> removeMitarbeiter(@PathVariable long id, @RequestBody List<Long> mitarbeiterIds) {
        teamSerivce.removeMitarbeiterFromTeam(id, mitarbeiterIds);
        return new ResponseEntity<String>("Mitarbeiter(s) removed!", HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/team/delete/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable long id) {
        teamSerivce.deleteTeam(id);
        return new ResponseEntity<String>("Team with id: " + id + " deleted!", HttpStatus.OK);
    }
}