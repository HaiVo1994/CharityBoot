package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Account;
import org.haivo_charity.charity.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {
//    Iterable<Volunteer> findAllByDonates(Donate donate);

    @Query("SELECT vol FROM Volunteer vol INNER JOIN Donate don ON don.volunteer = vol " +
            "WHERE SUM(don.amount)>= :amount " +
            "GROUP BY vol")
    Iterable<Volunteer> getListVolunteerDonateMore(@Param("amount") Long amount);

    Volunteer findFirstByNameOrderByIdDesc(String name);
    Volunteer findFirstByEmailOrderByIdDesc(String email);
    Volunteer findFirstByPhoneOrderByIdDesc(String phone);
    Volunteer findFirstByNameAndEmailAndPhone(String name, String email, String phone);

    @Query("SELECT v FROM Volunteer v " +
            "WHERE v.donates.size > 0")
    List<Volunteer> findAllByDonatesIsNotNull();

    Volunteer findByAccount(Account account);
}
