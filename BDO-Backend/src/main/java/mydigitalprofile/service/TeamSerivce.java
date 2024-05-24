package mydigitalprofile.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Team;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.TeamDto;
import mydigitalprofile.repository.MitarbeiterRepository;
import mydigitalprofile.repository.SkillRepository;
import mydigitalprofile.repository.TeamRepository;

@Service
public class TeamSerivce {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    private SkillRepository skillRepository;

    public Long createNewTeam(TeamDto dto) {
        Team team = teamRepository.findByTeamName(dto.getTeamName());
        if (team != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This team name is not available!");
        }
        List<Mitarbeiter> mitarbeiters = getMitarbeiterList(dto.getMitarbeiterUsernames());
        Team newTeam = new Team(dto.getTeamName());
        newTeam = teamRepository.save(newTeam);
        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            newTeam.addMitarbeiter(mitarbeiter);
        }
        mitarbeiterRepository.saveAll(mitarbeiters);
        return newTeam.getTeamID();
    }

    public TeamDto getTeamByTeamName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        if (team != null) {
            TeamDto dto = new TeamDto();
            dto.setTeamName(team.getTeamName());
            for (Mitarbeiter mitarbeiter : team.getMitarbeiters()) {
                dto.getMitarbeiterUsernames().add(mitarbeiter.getUsername());
            }
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team: " + teamName + " does not exist!");
        }
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = new ArrayList<TeamDto>();
        for (Team team : teams) {
            TeamDto teamDto = new TeamDto();
            teamDto.setTeamName(team.getTeamName());
            for (Mitarbeiter mitarbeiter : team.getMitarbeiters()) {
                teamDto.getMitarbeiterUsernames().add(mitarbeiter.getUsername());
            }
            teamDtos.add(teamDto);
        }

        return teamDtos;
    }


    public List<MitarbeiterDto> getTeamMembersByUsername(String username) {
        List<MitarbeiterDto> mitarbeiters = new ArrayList<MitarbeiterDto>();
        Long teamId = teamRepository.findTeamIdByUsername(username);
        if (teamId != null) {
            mitarbeiters = teamRepository.findAllMitarbeiterByTeamId(teamId);
        }
        if (mitarbeiters.size() > 0) {
            for (MitarbeiterDto mitarbeiterDto : mitarbeiters) {
                List<String> skills = skillRepository.findSkillsByUsername(mitarbeiterDto.getUsername());
                if (!skills.isEmpty()) {
                    mitarbeiterDto.setSkills(skills);
                }
            }
        }

        return mitarbeiters;
    }

    public void addMitarbeiter(String teamName, List<String> mitarbeiterUsernames) {
        Team team = teamRepository.findByTeamName(teamName);
        if (team != null) {
            List<Mitarbeiter> mitarbeiters = getMitarbeiterList(new HashSet<String>(mitarbeiterUsernames));
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                team.addMitarbeiter(mitarbeiter);
                mitarbeiterRepository.save(mitarbeiter);
            }
            teamRepository.save(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team: " + teamName + " does not exist!");
        }
    }

    public void removeMitarbeiterFromTeam(String teamName, List<String> mitarbeiterUsernames) {
        Team team = teamRepository.findByTeamName(teamName);
        if (team != null) {
            List<Mitarbeiter> mitarbeitersToRemove = getMitarbeiterListToRemove(team.getMitarbeiters(),
                    mitarbeiterUsernames);
            for (Mitarbeiter mitarbeiter : mitarbeitersToRemove) {
                team.removeMitarbeiter(mitarbeiter);
                mitarbeiterRepository.save(mitarbeiter);
            }
            teamRepository.save(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team: " + teamName + " does not exist!");
        }
    }



    public void deleteTeam(String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        if (team != null) {
            for (Mitarbeiter mitarbeiter : team.getMitarbeiters()) {
                mitarbeiter.setTeam(null);
                mitarbeiterRepository.save(mitarbeiter);
            }
            team.getMitarbeiters().clear();
            teamRepository.delete(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team: " + teamName + " does not exist!");
        }
    }



    private List<Mitarbeiter> getMitarbeiterList(Set<String> mitarbeiterUsernames) {
        List<Mitarbeiter> mitarbeiters = new ArrayList<Mitarbeiter>();
        for (String username : mitarbeiterUsernames) {
            Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(username);
            if (mitarbeiter == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User: " + username + " does not exist!");
            }
            mitarbeiters.add(mitarbeiter);
        }
        return mitarbeiters;
    }

    private List<Mitarbeiter> getMitarbeiterListToRemove(Set<Mitarbeiter> mitarbeiters, List<String> mitarbeiterUsernames) {
        List<Mitarbeiter> mitarbeitersToRemove = new ArrayList<Mitarbeiter>();
        for (String username : mitarbeiterUsernames) {
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter.getUsername().equals(username)) {
                    mitarbeitersToRemove.add(mitarbeiter);
                    break;
                }
            }
        }

        return mitarbeitersToRemove;
    }
}