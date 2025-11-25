package com.banconova.repo;

import com.banconova.domain.Account;
import com.banconova.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findTop10ByAccountOrderByFechaDesc(Account account);
    List<Movement> findByAccountOrderByFechaDesc(Account account);
}
