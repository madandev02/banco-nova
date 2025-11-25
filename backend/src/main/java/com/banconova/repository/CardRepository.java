
package com.banconova.repository;

import com.banconova.domain.entity.Card;
import com.banconova.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByOwner(User owner);
}
