package org.haivo_charity.charity.repository;

import org.haivo_charity.charity.model.Account;
import org.haivo_charity.charity.model.Event;
import org.haivo_charity.charity.model.RegisterEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterEventRepository extends JpaRepository<RegisterEvent, Long> {
    RegisterEvent findByAccountAndEvent(Account account, Event event);

    @Query("SELECT r FROM RegisterEvent r " +
            "WHERE r.account = :account " +
            "AND r.accept = false " +
            "ORDER BY r.register_at DESC")
    Page<RegisterEvent> getListRegisterNotAccepted(@Param("account") Account account, Pageable pageable);
    @Query("SELECT COUNT(r) FROM RegisterEvent r " +
            "WHERE r.account = :account " +
            "AND r.accept = false ")
    int countAmountRegisterNotAccepted(@Param("account") Account account);

    @Query("SELECT r FROM RegisterEvent r " +
            "WHERE r.account = :account " +
            "AND r.accept = true " +
            "ORDER BY r.register_at DESC")
    Page<RegisterEvent> getListRegisterAccepted(@Param("account") Account account, Pageable pageable);
    @Query("SELECT COUNT(r) FROM RegisterEvent r " +
            "WHERE r.account = :account " +
            "AND r.accept = true ")
    int countAmountRegisterAccepted(@Param("account") Account account);

    @Query("SELECT r FROM RegisterEvent r " +
            "WHERE r.account = :account " +
            "AND r.finish = true " +
            "ORDER BY r.register_at DESC")
    Page<RegisterEvent> getListRegisterFinish(@Param("account") Account account, Pageable pageable);
    @Query("SELECT COUNT(r) FROM RegisterEvent r " +
            "WHERE r.finish = :account " +
            "AND r.accept = true ")
    int countAmountRegisterFinish(@Param("account") Account account);

    Page<RegisterEvent> findAllByEvent(Event event, Pageable pageable);
    int countAllByEvent(Event event);
    Iterable<RegisterEvent> findAllByEvent(Event event);

    @Query("SELECT count (r) FROM RegisterEvent r " +
            "WHERE r.event = :event " +
            "AND r.accept = true ")
    long countAcceptedByEvent(@Param("event") Event event);

    @Query("SELECT r.event FROM RegisterEvent r " +
            "WHERE r.account = :account")
    Page<Event> listEventRegisterByAccount(@Param("account") Account account, Pageable pageable);

    @Query("SELECT count(r) FROM RegisterEvent r " +
            "WHERE r.account = :account")
    int countEventRegisterByAccount(@Param("account") Account account);
}
