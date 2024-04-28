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

import mydigitalprofile.model.dto.ProjektDto;

@RestController
@RequestMapping(path = "/api/")
public class ProjektController {

	@PostMapping("admin/project/create")
	public ResponseEntity<String> createProject(@RequestBody ProjektDto projektDto) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping("user/project/{projectid}")
	public ResponseEntity<String> getProjectById(@PathVariable long projectid) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping("admin/project/all")
	public ResponseEntity<String> getAllProjects() {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping("admin/project/update/{projectid}")
	public ResponseEntity<String> updateProjectById(@PathVariable long projectid, @RequestBody ProjektDto projektDto) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping("admin/delete/project/{projectid}")
	public ResponseEntity<String> deleteProject(@PathVariable long projectid) {
		// TODO:
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}
}
