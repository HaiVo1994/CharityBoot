package org.haivo_charity.charity.service;

import org.haivo_charity.charity.model.Donate;
import org.haivo_charity.charity.model.Vote;

import java.util.List;

public interface DonateService {
//    Iterable<Donate> findAllByVolunteer(Volunteer volunteer);
    Iterable<Donate> findAll();
    Donate findById(Long id);
    Iterable<Donate> findAllByVote(Vote vote);
    void save(Donate donate);
    void remove(Long id);
    Long getTotalDonate();
    Long getTotalDonateOfVote(Vote vote);

    List<Donate> getListDonateOfVote(Vote vote, int page, int size);
    int getAmountPage(Vote vote, int size);
}
