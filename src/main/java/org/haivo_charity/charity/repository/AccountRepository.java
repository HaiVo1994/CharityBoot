package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findFirstByUsername(String username);
    @Query("SELECT COUNT (a) FROM Account a " +
            "WHERE a.registerEvents.size>0")
    long countVolunteer();
}
