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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.service.MitarbeiterService;

@RestController
@RequestMapping(path = "/api/")
public class MitarbeiterController {

	@Autowired
	private MitarbeiterService mitarbeiterService;

	@ApiOperation(value = "Creates a new user and returns his/her ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "New user was successfully saved and his/her ID is in the response body"),
			@ApiResponse(code = 400, message = "If the username was not available."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@PostMapping(path = "register")
	public ResponseEntity<Long> createMitarbeiter(
			@ApiParam(name = "User DTO that has the information of the new user", required = true) @RequestBody MitarbeiterDto mitarbeiterDto) {
		long userId = mitarbeiterService.saveNewMitarbeiter(mitarbeiterDto);
		return new ResponseEntity<>(userId, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns a user DTO by its username, this end-point could be used after a successful login.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns the User data."),
			@ApiResponse(code = 400, message = "In case the username is missing in the request."),
			@ApiResponse(code = 401, message = "If the user is not authenticated."),
			@ApiResponse(code = 404, message = "In case the username is not found in the database."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@GetMapping(path = "user/{username}/user")
	public ResponseEntity<MitarbeiterDto> getUser(
			@ApiParam(name = "Username", required = true) @PathVariable String username) {
		MitarbeiterDto mitarbeiterDto = mitarbeiterService.findUserByUsername(username);
		return new ResponseEntity<>(mitarbeiterDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns all users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns a list of all users."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@GetMapping(path = "admin/all")
	public ResponseEntity<List<MitarbeiterDto>> getUsers() {
		List<MitarbeiterDto> list = mitarbeiterService.getAllUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@ApiOperation(value = "Updates a user's information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User updated successfully."),
			@ApiResponse(code = 400, message = "In case the username is missing in the request."),
			@ApiResponse(code = 404, message = "In case the username is not found in the database."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@PutMapping(path = "user/{username}")
	public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody MitarbeiterDto userDto) {
		mitarbeiterService.updateUser(username, userDto);
		return new ResponseEntity<String>("User: " + username + " updated!", HttpStatus.OK);
	}

	@ApiOperation(value = "Updates a user's role")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User's role updated successfully."),
			@ApiResponse(code = 400, message = "In case the username is missing in the request."),
			@ApiResponse(code = 404, message = "In case the username is not found in the database."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@PutMapping(path = "admin/updateMitarbeiterRolle")
	public ResponseEntity<String> updateUserRole(@RequestBody MitarbeiterRolleDto request) {
		mitarbeiterService.updateUserRole(request);
		return new ResponseEntity<>("User: " + request.getUsername() + " updated!", HttpStatus.OK);
	}

	@ApiOperation(value = "Updates a user's skills")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User's skills updated successfully."),
			@ApiResponse(code = 400, message = "In case the username is missing in the request."),
			@ApiResponse(code = 404, message = "In case the username is not found in the database."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@PutMapping(path = "admin/updateMitarbeiterSkill/{username}")
	public ResponseEntity<String> updateUserSkill(@PathVariable String username, @RequestBody List<String> skills) {
		mitarbeiterService.addSkillsToUser(username, skills);
		return new ResponseEntity<String>("the skills of user: " + username + " updated!", HttpStatus.OK);
	}

	@ApiOperation(value = "Deletes a user by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User deleted successfully."),
			@ApiResponse(code = 400, message = "In case the ID is missing in the request."),
			@ApiResponse(code = 404, message = "In case the ID is not found in the database."),
			@ApiResponse(code = 500, message = "In case of an internal server error.") })
	@DeleteMapping(path = "admin/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		mitarbeiterService.deleteUser(id);
		return new ResponseEntity<String>("User with id: " + id + " is deleted successfully ", HttpStatus.OK);
	}
}
