package com.banconova.repo;

import com.banconova.domain.Beneficiary;
import com.banconova.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByUserOrderByAliasAsc(User user);
    Optional<Beneficiary> findByIdAndUser(Long id, User user);
}
