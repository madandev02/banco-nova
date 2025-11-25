
package com.banconova.repository;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner(User owner);
    Optional<Account> findByAccountNumber(String accountNumber);
}
