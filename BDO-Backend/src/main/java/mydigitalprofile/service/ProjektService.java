package mydigitalprofile.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mydigitalprofile.model.Projekt;
import mydigitalprofile.model.Team;
import mydigitalprofile.model.dto.ProjektDto;
import mydigitalprofile.repository.ProjektRepository;
import mydigitalprofile.repository.TeamRepository;

@Service
public class ProjektService {

    @Autowired
    private ProjektRepository projektRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Long createNewProject(ProjektDto dto) {
        Projekt projekt = projektRepository.findByProjektName(dto.getProjektName());
        if (projekt != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This project name is not available!");
        }
        Date startDate = getDateFromString(dto.getStartDate());
        Date endDate = getDateFromString(dto.getStartDate());
        Projekt newProjekt = new Projekt(dto.getProjektName(), dto.getVolumina(), startDate, endDate,
                dto.getBeschreibung());
        newProjekt = projektRepository.save(newProjekt);
        if (newProjekt != null) {
            return newProjekt.getProjektID();
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Somthing went wrong while saving this project!");
        }
    }

    public ProjektDto getProjectById(long id) {
        Optional<Projekt> optional = projektRepository.findById(id);
        if (optional.isPresent()) {
            Projekt projekt = optional.get();
            String startDate = getStringFromDate(projekt.getStartDate());
            String endDate = getStringFromDate(projekt.getEndDate());
            ProjektDto projektDto = new ProjektDto(projekt.getProjektName(), projekt.getVolumina(), startDate, endDate,
                    projekt.getBeschreibung());
            return projektDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }


    public List<ProjektDto> getAllProjects() {
        List<ProjektDto> projektDtos = new ArrayList<ProjektDto>();
        List<Projekt> projekts = projektRepository.findAll();
        for (Projekt projekt : projekts) {
            String startDate = getStringFromDate(projekt.getStartDate());
            String endDate = getStringFromDate(projekt.getEndDate());
            ProjektDto projektDto = new ProjektDto(projekt.getProjektName(), projekt.getVolumina(), startDate, endDate,
                    projekt.getBeschreibung());
            projektDtos.add(projektDto);
        }
        return projektDtos;
    }

    public void updateProjektById(long id, ProjektDto dto) {
        Optional<Projekt> optional = projektRepository.findById(id);
        if (optional.isPresent()) {
            Projekt projekt = optional.get();
            if (dto.getProjektName() != null && !dto.getProjektName().isEmpty()) {
                projekt.setProjektName(dto.getProjektName());
            }
            if (dto.getStartDate() != null && !dto.getStartDate().isEmpty()) {
                Date startDate = getDateFromString(dto.getStartDate());
                projekt.setStartDate(startDate);
            }
            if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
                Date endDate = getDateFromString(dto.getEndDate());
                projekt.setEndDate(endDate);
            }
            if (dto.getVolumina() != null && !dto.getVolumina().isEmpty())  {
                projekt.setVolumina(dto.getVolumina());
            }
            if (dto.getBeschreibung() != null && !dto.getBeschreibung().isEmpty()) {
                projekt.setBeschreibung(dto.getBeschreibung());
            }
            projektRepository.save(projekt);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }
    public void addTeamToProject(long teamId, long projectid) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with id: " + teamId + " does not exist!");
        }
        Optional<Projekt> projektOptional = projektRepository.findById(projectid);
        if (projektOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project with id: " + projectid + " does not exist!");
        }
        Team team = teamOptional.get();
        Projekt projekt = projektOptional.get();
        team.setProjekt(projekt);
        projekt.setTeam(team);
        projektRepository.save(projekt);
        teamRepository.save(team);
    }

    public void deleteProjectById(long projectid) {
        Optional<Projekt> projektOptional = projektRepository.findById(projectid);
        if (projektOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project with id: " + projectid + " does not exist!");
        }
        Projekt projekt = projektOptional.get();
        Team team = projekt.getTeam();
        projekt.removeTeam();
        teamRepository.save(team);
        projektRepository.delete(projekt);
    }

    private Date getDateFromString(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender date is not valid!");
        }
    }

    private String getStringFromDate(Date date) {
        String pattern = "dd.MM.yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}