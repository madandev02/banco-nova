
package com.banconova.repository;

import com.banconova.domain.entity.Investment;
import com.banconova.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByOwner(User owner);
}
