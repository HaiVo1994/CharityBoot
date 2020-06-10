package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Vote;
import org.haivo_charity.charity.model.VoteImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteImageRepository extends JpaRepository<VoteImage, Long> {
    Iterable<VoteImage> findAllByVote(Vote vote);
//    VoteImage findFirstByVote(Vote vote);
    void deleteAllByVoteId(Long vote_id);

    @Query("SELECT vi FROM VoteImage vi " +
            "ORDER BY vi.id DESC")
    Page<VoteImage> getTopImage(Pageable pageable);
}
