package com.banconova.repo;

import com.banconova.domain.Investment;
import com.banconova.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUser(User user);
}
