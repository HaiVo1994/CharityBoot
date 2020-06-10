package org.haivo_charity.charity.formatter;

import org.haivo_charity.charity.model.Volunteer;
import org.haivo_charity.charity.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class VolunteerFormatter implements Formatter<Volunteer> {
    private VolunteerService volunteerService;

    @Autowired
    public VolunteerFormatter(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public Volunteer parse(String text, Locale locale) throws ParseException {
        return volunteerService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Volunteer object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName()
                + ", " + object.getEmail() + ", " + object.getPhone() + "]";
    }
}
