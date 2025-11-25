
package com.banconova.repository;

import com.banconova.domain.entity.Beneficiary;
import com.banconova.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByOwner(User owner);
    Optional<Beneficiary> findByOwnerAndRutAndBankAndAccountNumber(User owner, String rut, String bank, String accountNumber);
}
