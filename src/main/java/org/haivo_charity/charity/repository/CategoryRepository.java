package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Category;
import org.haivo_charity.charity.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Iterable<Category> findAllByVotes(Vote vote);
}
