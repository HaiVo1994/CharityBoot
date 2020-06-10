package org.haivo_charity.charity.service;

import org.haivo_charity.charity.model.Category;
import org.haivo_charity.charity.model.Vote;

public interface CategoryService {
    Iterable<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void remove(Long id);

    Iterable<Category> findAllByVote(Vote vote);
    long countVoteVyCategory(Category category);
    long countAll();
}
