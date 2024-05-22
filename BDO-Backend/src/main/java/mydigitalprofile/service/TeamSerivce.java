package mydigitalprofile.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        List<Mitarbeiter> mitarbeiters = getMitarbeiterList(dto.getMitarbeiterIds());
        Team newTeam = new Team(dto.getTeamName());
        newTeam = teamRepository.save(newTeam);
        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            newTeam.addMitarbeiter(mitarbeiter);
        }
        mitarbeiterRepository.saveAll(mitarbeiters);
        return newTeam.getTeamID();
    }

    public TeamDto getTeamById(long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            TeamDto dto = new TeamDto();
            dto.setTeamName(team.get().getTeamName());
            for (Mitarbeiter mitarbeiter : team.get().getMitarbeiters()) {
                dto.getMitarbeiterIds().add(mitarbeiter.getMaId());
            }
            return dto;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with id: " + id + " does not exist!");
        }
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = new ArrayList<TeamDto>();
        for (Team team : teams) {
            TeamDto teamDto = new TeamDto();
            teamDto.setTeamName(team.getTeamName());
            for (Mitarbeiter mitarbeiter : team.getMitarbeiters()) {
                teamDto.getMitarbeiterIds().add(mitarbeiter.getMaId());
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

    public void addMitarbeiter(long teamId, List<Long> mitarbeiterIds) {
        Optional<Team> optional = teamRepository.findById(teamId);
        if (optional.isPresent()) {
            Team team = optional.get();
            List<Mitarbeiter> mitarbeiters = getMitarbeiterList(new HashSet<Long>(mitarbeiterIds));
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                team.addMitarbeiter(mitarbeiter);
                mitarbeiterRepository.save(mitarbeiter);
            }
            teamRepository.save(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with id: " + teamId + " does not exist!");
        }
    }

    public void removeMitarbeiterFromTeam(long teamId, List<Long> mitarbeiterIds) {
        Optional<Team> optional = teamRepository.findById(teamId);
        if (optional.isPresent()) {
            Team team = optional.get();
            List<Mitarbeiter> mitarbeitersToRemove = getMitarbeiterListToRemove(team.getMitarbeiters(),
                    mitarbeiterIds);
            for (Mitarbeiter mitarbeiter : mitarbeitersToRemove) {
                team.removeMitarbeiter(mitarbeiter);
                mitarbeiterRepository.save(mitarbeiter);
            }
            teamRepository.save(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with id: " + teamId + " does not exist!");
        }
    }



    public void deleteTeam(long id) {
        Optional<Team> optional = teamRepository.findById(id);
        if (optional.isPresent()) {
            Team team = optional.get();
            for (Mitarbeiter mitarbeiter : team.getMitarbeiters()) {
                mitarbeiter.setTeam(null);
                mitarbeiterRepository.save(mitarbeiter);
            }
            team.getMitarbeiters().clear();
            teamRepository.delete(team);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with id: " + id + " does not exist!");
        }
    }



    private List<Mitarbeiter> getMitarbeiterList(Set<Long> mitarbeiterIds) {
        List<Mitarbeiter> mitarbeiters = new ArrayList<Mitarbeiter>();
        for (Long id : mitarbeiterIds) {
            Mitarbeiter mitarbeiter = mitarbeiterRepository.getById(id);
            if (mitarbeiter == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id: " + id + " does not exist!");
            }
            mitarbeiters.add(mitarbeiter);
        }
        return mitarbeiters;
    }

    private List<Mitarbeiter> getMitarbeiterListToRemove(Set<Mitarbeiter> mitarbeiters, List<Long> mitarbeiterIds) {
        List<Mitarbeiter> mitarbeitersToRemove = new ArrayList<Mitarbeiter>();
        for (Long id : mitarbeiterIds) {
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter.getMaId().equals(id)) {
                    mitarbeitersToRemove.add(mitarbeiter);
                    break;
                }
            }
        }

        return mitarbeitersToRemove;
    }
}