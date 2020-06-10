package org.haivo_charity.charity.service.impl;

import org.haivo_charity.charity.model.Vote;
import org.haivo_charity.charity.model.VoteImage;
import org.haivo_charity.charity.repository.VoteImageRepository;
import org.haivo_charity.charity.service.VoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteImageServiceImpl implements VoteImageService {
    @Autowired
    private VoteImageRepository voteImageRepository;

    @Override
    public Iterable<VoteImage> findAll() {
        return voteImageRepository.findAll();
    }

    @Override
    public Iterable<VoteImage> findAllByVote(Vote vote) {
        return voteImageRepository.findAllByVote(vote);
    }

    @Override
    public void save(VoteImage voteImage) {
        voteImageRepository.save(voteImage);
    }

    @Override
    public void remove(Long id) {
        voteImageRepository.deleteById(id);
    }

    @Override
    public void removeAllByVote(Vote vote) {
        voteImageRepository.deleteAllByVoteId(vote.getId());
    }

    @Override
    public List<VoteImage> getTopImage(int size) {
        return voteImageRepository.getTopImage(PageRequest.of(0, size)).toList();
    }
}
