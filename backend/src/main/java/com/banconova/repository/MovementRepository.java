
package com.banconova.repository;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findTop10ByAccountOrderByCreatedAtDesc(Account account);
}
