package org.haivo_charity.charity.formatter;

import org.haivo_charity.charity.model.Vote;
import org.haivo_charity.charity.service.VolunteerService;
import org.haivo_charity.charity.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class VoteFormatter implements Formatter<Vote> {
    private VoteService voteService;

    @Autowired
    public VoteFormatter(VoteService voteService) {
        this.voteService = voteService;
    }
    @Override
    public Vote parse(String text, Locale locale) throws ParseException {
        return voteService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Vote object, Locale locale) {
        return "[" + object.getId() + ", " +object.getTittle()
                + ", " + object.getBeginDate() + ", " + object.getGoal() + "]";
    }
}
