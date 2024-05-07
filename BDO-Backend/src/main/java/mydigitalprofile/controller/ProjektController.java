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

import mydigitalprofile.model.dto.ProjektDto;
import mydigitalprofile.service.ProjektService;

@RestController
@RequestMapping(path = "/api/")
public class ProjektController {

	@Autowired
	private ProjektService service;
	@PostMapping("admin/project/create")
	public ResponseEntity<Long> createProject(@RequestBody ProjektDto projektDto) {
		Long projektId = service.createNewProject(projektDto);
		return new ResponseEntity<>(projektId, HttpStatus.OK);
	}

	@GetMapping("user/project/{projectid}")
	public ResponseEntity<ProjektDto> getProjectById(@PathVariable long projectid) {
		ProjektDto dto = service.getProjectById(projectid);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("admin/project/all")
	public ResponseEntity<List<ProjektDto>> getAllProjects() {
		List<ProjektDto> projektDtos = service.getAllProjects();
		return new ResponseEntity<>(projektDtos, HttpStatus.OK);
	}

	@PutMapping("admin/project/update/{projectid}")
	public ResponseEntity<String> updateProjectById(@PathVariable long projectid, @RequestBody ProjektDto projektDto) {
		service.updateProjektById(projectid, projektDto);
		return new ResponseEntity<String>("Project with id: " + projectid + " updated!", HttpStatus.OK);
	}
	@PutMapping("admin/project/addTeam/{teamId}/toProject/{projectid}")
	public ResponseEntity<String> addTeamToProject(@PathVariable long teamId, @PathVariable long projectid) {
		service.addTeamToProject(teamId, projectid);
		return new ResponseEntity<String>("Project with id: " + projectid + " updated!", HttpStatus.OK);
	}

	@DeleteMapping("admin/delete/project/{projectid}")
	public ResponseEntity<String> deleteProject(@PathVariable long projectid) {
		service.deleteProjectById(projectid);
		return new ResponseEntity<String>("Project with id: " + projectid + " was deleted!", HttpStatus.OK);
	}
}