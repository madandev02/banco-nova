
package com.banconova.repository;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    Page<Movement> findByAccountOrderByCreatedAtDesc(Account account, Pageable pageable);
}
