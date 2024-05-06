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

import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.service.MitarbeiterService;

@RestController
@RequestMapping(path = "/api/")
public class MitarbeiterController {

	@Autowired
	private MitarbeiterService mitarbeiterService;

	@PostMapping(path = "register")
	public ResponseEntity<Long> createMitarbeiter(@RequestBody MitarbeiterDto mitarbeiterDto) {
		long userId = mitarbeiterService.saveNewMitarbeiter(mitarbeiterDto);
		return new ResponseEntity<>(userId, HttpStatus.OK);
	}


	@GetMapping(path = "user/{username}/user/{id}")
	public ResponseEntity<MitarbeiterDto> getUser(@PathVariable String username, @PathVariable long id) {
		MitarbeiterDto mitarbeiterDto = mitarbeiterService.findUserByIdAndUsername(username, id);
		return new ResponseEntity<>(mitarbeiterDto, HttpStatus.OK);
	}

	@GetMapping(path = "admin/all")
	public ResponseEntity<List<MitarbeiterDto>> getUsers() {
		List<MitarbeiterDto> list = mitarbeiterService.getAllUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping(path = "user/{username}")
	public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody MitarbeiterDto userDto) {
		mitarbeiterService.updateUser(username, userDto);
		return new ResponseEntity<String>("User: " + username + " updated!", HttpStatus.OK);
	}

	@PutMapping(path = "admin/updateMitarbeiterRolle")
	public ResponseEntity<String> updateUserRole(@RequestBody MitarbeiterRolleDto request) {
		mitarbeiterService.updateUserRole(request);
		return new ResponseEntity<>("User: " + request.getUsername() + " updated!", HttpStatus.OK);
	}

	@PutMapping(path = "admin/updateMitarbeiterSkill/{username}")
	public ResponseEntity<String> updateUserSkill(@PathVariable String username, @RequestBody List<String> skills) {
		mitarbeiterService.addSkillsToUser(username, skills);
		return new ResponseEntity<String>("the skills of user: " + username + " updated!", HttpStatus.OK);
	}

	@DeleteMapping(path = "admin/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		mitarbeiterService.deleteUser(id);
		return new ResponseEntity<String>("User with id: " + id + " is deleted successfully ", HttpStatus.OK);
	}
}
