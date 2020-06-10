package org.haivo_charity.charity.service;

import org.haivo_charity.charity.model.Vote;
import org.haivo_charity.charity.model.VoteImage;

import java.util.List;

public interface VoteImageService {
    Iterable<VoteImage> findAll();
    Iterable<VoteImage> findAllByVote(Vote vote);
    void save(VoteImage voteImage);
    void remove(Long id);
    void removeAllByVote(Vote vote);
    List<VoteImage> getTopImage(int size);
}
