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

import mydigitalprofile.model.KalenderEvent;
import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.dto.KalenderEventDto;
import mydigitalprofile.repository.KalenderEventRepository;
import mydigitalprofile.repository.MitarbeiterRepository;

@Service
public class KalenderEventService {
    @Autowired
    private KalenderEventRepository kalenderEventRepository;
    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    // Methode zum Hinzufügen eines Kalendereintrags für einen Benutzer
    public Long addKalenderEvent(long userId, KalenderEventDto dto) {
        Optional<Mitarbeiter> optional = mitarbeiterRepository.findById(userId);
        if (optional.isPresent()) { // Überprüfen, ob der Benutzer vorhanden ist
            checkEventDto(dto); // Validierung des DTO
            Date fromDate = getDateFromString(dto.getVonDatum());
            Date toDate = getDateFromString(dto.getBisDatum());
            // Erstellen eines neuen Kalendereintrags
            KalenderEvent newEvent = new KalenderEvent(fromDate, dto.getTyp(), toDate, dto.getBeschreibung());
            newEvent = kalenderEventRepository.save(newEvent); // Speichern des Eintrags in der Datenbank
            Mitarbeiter mitarbeiter = optional.get();
            mitarbeiter.addKalenderEvent(newEvent); // Hinzufügen des Eintrags zum Benutzer
            mitarbeiterRepository.save(mitarbeiter); // Aktualisieren des Benutzers in der Datenbank
            return newEvent.getKalenderEventId(); // Rückgabe der ID des neuen Eintrags
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
    }

    // Methode zum Abrufen aller Kalendereinträge für einen Benutzer
    public List<KalenderEventDto> getAllEventForUserById(long userId) {
        List<KalenderEventDto> kalenderEventDtos = new ArrayList<KalenderEventDto>();
        Optional<Mitarbeiter> optional = mitarbeiterRepository.findById(userId);
        if (optional.isPresent()) { // Überprüfen, ob der Benutzer vorhanden ist
            List<KalenderEvent> events = kalenderEventRepository.findKalenderEventsForUserById(userId);
            // Iteration über die gefundenen Kalendereinträge und Umwandlung in DTOs
            for (KalenderEvent kalenderEvent : events) {
                String fromDate = getStringFromDate(kalenderEvent.getVonDatum());
                String toDate = getStringFromDate(kalenderEvent.getBisDatum());
                KalenderEventDto dto = new KalenderEventDto(fromDate, toDate, kalenderEvent.getTyp(), kalenderEvent.getBeschreibung());
                kalenderEventDtos.add(dto); // Hinzufügen des DTOs zur Liste
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
        return kalenderEventDtos; // Rückgabe der Liste von DTOs
    }

    // Methode zum Aktualisieren eines Kalendereintrags
    public void updateEvent(long eventId, KalenderEventDto dto) {
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(eventId);
        if (optional.isPresent()) { // Überprüfen, ob der Eintrag vorhanden ist
            KalenderEvent kalenderEvent = optional.get();
            // Aktualisieren der Eigenschaften des Eintrags basierend auf dem DTO
            if (dto.getTyp() != null && !dto.getTyp().isEmpty()) {
                kalenderEvent.setTyp(dto.getTyp());
            }
            if (dto.getBeschreibung() != null && !dto.getBeschreibung().isEmpty()) {
                kalenderEvent.setBeschreibung(dto.getBeschreibung());
            }
            if (dto.getVonDatum() != null && !dto.getVonDatum().isEmpty()) {
                kalenderEvent.setVonDatum(getDateFromString(dto.getVonDatum()));
            }
            if (dto.getBisDatum() != null && !dto.getBisDatum().isEmpty()) {
                kalenderEvent.setBisDatum(getDateFromString(dto.getBisDatum()));
            }
            kalenderEventRepository.save(kalenderEvent); // Speichern der Änderungen in der Datenbank
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender event's id is not valid!");
        }
    }

    // Methode zum Löschen eines Kalendereintrags
    public void deleteEvent(long eventId) {
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(eventId);
        if (optional.isPresent()) { // Überprüfen, ob der Eintrag vorhanden ist
            KalenderEvent kalenderEvent = optional.get();
            Mitarbeiter mitarbeiter = kalenderEvent.getMitarbeiter();
            mitarbeiter.removeKalenderEvent(kalenderEvent); // Entfernen des Eintrags aus dem Benutzerprofil
            mitarbeiterRepository.save(mitarbeiter); // Aktualisieren des Benutzers in der Datenbank
            kalenderEventRepository.delete(kalenderEvent); // Löschen des Eintrags aus der Datenbank
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender event's id is not valid!");
        }
    }

    // Hilfsmethode zur Validierung des Kalender-DTOs
    private void checkEventDto(KalenderEventDto dto) {
        if (dto.getVonDatum() == null || dto.getBisDatum() == null || dto.getTyp() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender request is not Valid!");
        }
    }

    // Hilfsmethode zur Konvertierung von Datum-String in ein Date-Objekt
    private Date getDateFromString(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender event date is not valid!");
        }
    }

    // Hilfsmethode zur Konvertierung von Date-Objekt in Datum-String
    private String getStringFromDate(Date date) {
        String pattern = "dd.MM.yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
