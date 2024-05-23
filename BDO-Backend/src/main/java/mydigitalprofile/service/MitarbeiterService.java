package mydigitalprofile.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mydigitalprofile.model.Address;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Skill;
import mydigitalprofile.model.dto.MitarbeiterDto;
import mydigitalprofile.model.dto.MitarbeiterRolleDto;
import mydigitalprofile.repository.AddressRepository;
import mydigitalprofile.repository.MitarbeiterRepository;
import mydigitalprofile.repository.SkillRepository;

@Service
public class MitarbeiterService {

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SkillRepository skillRepository;

    public Long saveNewMitarbeiter(MitarbeiterDto dto) {
        checkIfUserExist(dto);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dto.getPasswort());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date geburtstag = null;

        try {
            geburtstag = formatter.parse(dto.getGeburtsdatum());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        Address address = new Address(dto.getStrasse(), dto.getHausNr(), dto.getPlz(), dto.getOrt());

        Mitarbeiter mitarbeiter = new Mitarbeiter(dto.getPnr(), dto.getVorname(), dto.getNachname(), dto.getUsername(),
                encodedPassword, geburtstag, dto.getStandort(), dto.getKarriereLevel(), dto.getRolle(), address);

        mitarbeiter = mitarbeiterRepository.save(mitarbeiter);
        if (mitarbeiter.getMaId() != null) {
            return mitarbeiter.getMaId();
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Somthing went wrong while saving this user!");
        }

    }

    public MitarbeiterDto findUserByUsername(String username) {

        MitarbeiterDto mitarbeiter = mitarbeiterRepository.findDtoByUsername(username);
        List<String> skills = skillRepository.findSkillsByUsername(username);

        if (mitarbeiter != null) {
            if (!skills.isEmpty()) {
                mitarbeiter.setSkills(skills);
            }
            return mitarbeiter;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public List<MitarbeiterDto> getAllUsers() {
        List<MitarbeiterDto> mitarbeiters = mitarbeiterRepository.findAllMitarbeiter();
        for (MitarbeiterDto mitarbeiterDto : mitarbeiters) {
            List<String> skills = skillRepository.findSkillsByUsername(mitarbeiterDto.getUsername());
            if (!skills.isEmpty()) {
                mitarbeiterDto.setSkills(skills);
            }
        }

        return mitarbeiters;
    }



    public void updateUser(String username, MitarbeiterDto dto) {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(username);
        if (mitarbeiter != null) {
            Address address = mitarbeiter.getAddress();
            if (dto.getStrasse() != null && !dto.getStrasse().isEmpty()) {
                address.setStrasse(dto.getStrasse());
            }
            if (dto.getPlz() != null && !dto.getPlz().isEmpty()) {
                address.setPlz(dto.getPlz());
            }
            if (dto.getHausNr() != null && !dto.getHausNr().isEmpty()) {
                address.setHausNr(dto.getHausNr());
            }
            if (dto.getOrt() != null && !dto.getOrt().isEmpty()) {
                address.setOrt(dto.getOrt());
            }
            if (dto.getStandort() != null && !dto.getStandort().isEmpty()) {
                mitarbeiter.setStandort(dto.getStandort());
            }

            addressRepository.save(address);
            mitarbeiterRepository.save(mitarbeiter);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
    }


    public void updateUserRole(MitarbeiterRolleDto rolleDto) {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(rolleDto.getUsername());
        if (mitarbeiter != null) {
            mitarbeiter.setRolle(rolleDto.getRolle());
            mitarbeiterRepository.save(mitarbeiter);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
    }


    public void addSkillsToUser(String username, List<String> skills) {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(username);
        Set<Skill> skillSet = new HashSet<>();

        for (String skillName : skills) {
            Skill skill = new Skill(skillName);
            skill = skillRepository.save(skill);
            skillSet.add(skill);
        }
        mitarbeiter.addSkills(skillSet);
        mitarbeiter = mitarbeiterRepository.save(mitarbeiter);

    }

    public void deleteUser(long id) {
        Optional<Mitarbeiter> optionalMitarbeiter = mitarbeiterRepository.findById(id);
        if (optionalMitarbeiter.isPresent()) {
            mitarbeiterRepository.delete(optionalMitarbeiter.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
    }

    private void checkIfUserExist(MitarbeiterDto mitarbeiterDto) {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(mitarbeiterDto.getUsername());
        if (mitarbeiter != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is not available!");
        }
    }

}
