package com.banconova.repo;

import com.banconova.domain.Transfer;
import com.banconova.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findTop10ByUserOrderByFechaDesc(User user);
}
