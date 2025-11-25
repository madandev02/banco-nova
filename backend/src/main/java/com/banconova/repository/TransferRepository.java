
package com.banconova.repository;

import com.banconova.domain.entity.Transfer;
import com.banconova.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByFromAccountOrToAccount(Account from, Account to);
}
