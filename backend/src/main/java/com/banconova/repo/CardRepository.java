package com.banconova.repo;

import com.banconova.domain.Card;
import com.banconova.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUser(User user);
}
