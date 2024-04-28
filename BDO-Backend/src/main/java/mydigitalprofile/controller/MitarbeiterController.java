package mydigitalprofile.controller;

import java.util.List;

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

import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.model.dto.loginDto;

@RestController
@RequestMapping(path = "/api/")
public class MitarbeiterController {

	@PostMapping(path = "register")
	public ResponseEntity<String> createMitarbeiter(@RequestBody MitarbeiterDto mitarbeiterDto) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PostMapping(path = "login")
	public ResponseEntity<String> login(@RequestBody loginDto loginDto) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping(path = "user/{username}/user/{id}")
	public ResponseEntity<String> getUser(@PathVariable String username, @PathVariable long id) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping(path = "admin/all")
	public ResponseEntity<String> getUsers() {
		// TODO
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping(path = "user/{username}")
	public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody MitarbeiterDto userDto) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping(path = "admin/updateMitarbeiterRolle")
	public ResponseEntity<String> updateUserAsAdmin(@RequestBody MitarbeiterRolleDto request) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping(path = "admin/updateMitarbeiterSkill/{id}")
	public ResponseEntity<String> updateUserSkill(@RequestBody List<String> skills) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(path = "admin/delete/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable int userID) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}
}
