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

    public Long addKalenderEvent(long userId, KalenderEventDto dto) {
        Optional<Mitarbeiter> optional = mitarbeiterRepository.findById(userId);
        if (optional.isPresent()) {
            checkEventDto(dto);
            Date fromDate = getDateFromString(dto.getVonDatum());
            Date toDate = getDateFromString(dto.getBisDatum());
            KalenderEvent newEvent = new KalenderEvent(fromDate, dto.getTyp(), toDate, dto.getBeschreibung());
            newEvent = kalenderEventRepository.save(newEvent);
            Mitarbeiter mitarbeiter = optional.get();
            mitarbeiter.addKalenderEvent(newEvent);
            mitarbeiterRepository.save(mitarbeiter);
            return newEvent.getKalenderEventId();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
    }

    public List<KalenderEventDto> getAllEventForUserById(long userId) {
        List<KalenderEventDto> kalenderEventDtos = new ArrayList<KalenderEventDto>();
        Optional<Mitarbeiter> optional = mitarbeiterRepository.findById(userId);
        if (optional.isPresent()) {
            List<KalenderEvent> events = kalenderEventRepository.findKalenderEventsForUserById(userId);
            for (KalenderEvent kalenderEvent : events) {
                String fromDate = getStringFromDate(kalenderEvent.getVonDatum());
                String toDate = getStringFromDate(kalenderEvent.getBisDatum());
                KalenderEventDto dto = new KalenderEventDto(fromDate, toDate, kalenderEvent.getTyp(), kalenderEvent.getBeschreibung());
                kalenderEventDtos.add(dto);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not available!");
        }
        return kalenderEventDtos;
    }

    public void updateEvent(long eventId, KalenderEventDto dto) {
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(eventId);
        if (optional.isPresent()) {
            KalenderEvent kalenderEvent = optional.get();
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
            kalenderEventRepository.save(kalenderEvent);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender event's id is not valid!");
        }
    }


    public void deleteEvent(long eventId) {
        Optional<KalenderEvent> optional = kalenderEventRepository.findById(eventId);
        if (optional.isPresent()) {
            KalenderEvent kalenderEvent = optional.get();
            Mitarbeiter mitarbeiter = kalenderEvent.getMitarbeiter();
            mitarbeiter.removeKalenderEvent(kalenderEvent);
            mitarbeiterRepository.save(mitarbeiter);
            kalenderEventRepository.delete(kalenderEvent);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender event's id is not valid!");
        }
    }

    private void checkEventDto(KalenderEventDto dto) {
        if (dto.getVonDatum() == null || dto.getBisDatum() == null || dto.getTyp() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Calender request is not Valid!");
        }
    }
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
    private String getStringFromDate(Date date) {
        String pattern = "dd.MM.yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}

