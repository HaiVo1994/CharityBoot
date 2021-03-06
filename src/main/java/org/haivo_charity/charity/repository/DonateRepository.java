package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Donate;
import org.haivo_charity.charity.model.Volunteer;
import org.haivo_charity.charity.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {
    Iterable<Donate> findAllByVolunteer(Volunteer volunteer);
    Iterable<Donate> findAllByVote(Vote vote);
    Page<Donate> findAllByVote(Vote vote, Pageable pageable);
    int countAllByVote(Vote vote);

    @Query("SELECT SUM(amount) FROM Donate")
    Long sumDonate();

    @Query("SELECT SUM(amount) FROM Donate WHERE vote = :vote")
    Long sumDonateOfVote(@Param("vote") Vote vote);

    @Query("SELECT distinct d.vote  FROM Donate d " +
            "WHERE d.volunteer = :volunteer " +
            "GROUP BY d.vote")
    Page<Vote> getListAccountDonate(@Param("volunteer") Volunteer volunteer, Pageable pageable);
    @Query("SELECT distinct d.vote  FROM Donate d " +
            "WHERE d.volunteer = :volunteer " +
            "GROUP BY d.vote")
    List<Vote> getListAccountDonate(@Param("volunteer") Volunteer volunteer);
    @Query("SELECT SUM(d.amount) FROM Donate d " +
            "WHERE d.volunteer = :volunteer " +
            "AND d.vote = :vote")
    long amountAccountForVote(@Param("volunteer") Volunteer volunteer, @Param("vote") Vote vote);

}
