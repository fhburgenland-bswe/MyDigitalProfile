package databasemodels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import mydigitalprofile.MyDigitalProfileApplication;
import mydigitalprofile.model.Address;
import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.KalenderEvent;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.Projekt;
import mydigitalprofile.model.Rolle;
import mydigitalprofile.model.Skill;
import mydigitalprofile.model.Team;
import mydigitalprofile.repository.AddressRepository;
import mydigitalprofile.repository.KalenderEventRepository;
import mydigitalprofile.repository.MitarbeiterRepository;
import mydigitalprofile.repository.ProjektRepository;
import mydigitalprofile.repository.SkillRepository;
import mydigitalprofile.repository.TeamRepository;

@SpringBootTest(classes = MyDigitalProfileApplication.class, properties = "spring.datasource.url=jdbc:h2:mem:testdb1")
@ActiveProfiles("mem")
public class ModelsTest {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private KalenderEventRepository kalenderEventRepository;

	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;

	@Autowired
	private ProjektRepository projektRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private TeamRepository teamRepository;

	private void init() throws ParseException {

		Address address1 = new Address("TestStr", "1/2", "1210", "Wien");
		Skill skill1 = new Skill("Skill-1");
		Skill skill2 = new Skill("Skill-2");
		String stringDateFrom = "22-01-2023";
		String stringDateTo = "22-02-2023";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateFrom = formatter.parse(stringDateFrom);
		Date dataTo = formatter.parse(stringDateTo);
		KalenderEvent event1 = new KalenderEvent(dateFrom, "Urlaub", dataTo, "1-Urlaub");

		Set<Skill> skills = new HashSet<>();
		Set<KalenderEvent> events = new HashSet<>();
		String stringBirthday = "09-02-2000";
		Date birthday = formatter.parse(stringBirthday);
		Mitarbeiter mitarbeiter = new Mitarbeiter("pnr-1", "Pob", "Foo", "username", "password", birthday, "Wien",
				CareerLevel.JUNIOR_CONSULTANT, Rolle.ROLE_USER, address1, null, skills, events);

		kalenderEventRepository.save(event1);
		skillRepository.save(skill1);
		skillRepository.save(skill2);

		mitarbeiter.addKalenderEvent(event1);
		mitarbeiter.addSkill(skill1);
		mitarbeiter.addSkill(skill2);
		mitarbeiterRepository.save(mitarbeiter);
		kalenderEventRepository.save(event1);
		skillRepository.save(skill1);
		skillRepository.save(skill2);

		Projekt projekt = new Projekt("Projek-1", "smthn", dateFrom, dataTo, "DESC");
		projekt = projektRepository.save(projekt);
		Team team = new Team("Team-1", projekt, new HashSet<Mitarbeiter>());
		team.addMitarbeiter(mitarbeiter);
		teamRepository.save(team);
		mitarbeiterRepository.save(mitarbeiter);

	}

	@Test
	public void modelTest() {
		try {
			init();
		} catch (ParseException e) {
			fail();
		}

		List<Mitarbeiter> mitarbeiters = mitarbeiterRepository.findAll();
		List<Address> addresses = addressRepository.findAll();
		List<Team> teams = teamRepository.findAll();
		List<Projekt> projekts = projektRepository.findAll();

		assertTrue(mitarbeiters.size() > 0);
		assertTrue(addresses.size() > 0);
		assertTrue(teams.size() > 0);
		assertTrue(projekts.size() > 0);

		Mitarbeiter mitarbeiter = mitarbeiters.get(0);
		Team team = teams.get(0);

		assertTrue(mitarbeiter!=null);
		assertTrue(mitarbeiter.getSkills().iterator().next() != null);
		assertTrue(mitarbeiter.getKalenderEvents().size() > 0);

		assertTrue(team.getProjekt() != null);
		assertEquals("Team-1", team.getTeamName());

		assertEquals("Projek-1", team.getProjekt().getProjektName());

		mitarbeiterRepository.deleteAll();

	}
}
